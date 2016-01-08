package com.ccloomi.core.component.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.util.StringUtil;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SQLMaker
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年8月27日-下午10:27:03
 */
public abstract class SQLMaker implements SQLGod{
	protected final Logger log=LoggerFactory.getLogger(getClass());
	/**0查询1更新2删除3插入4创建数据库*/
	protected int type;
	protected StringBuilder sb;
	protected Map<String, String>table_alias;
	protected Map<String, BaseEntity>alias_entity;
	protected List<String>join_table_alias_on;
	protected List<String>columns;
	protected String where;
	protected List<String>andor;
	protected List<Object>values;
	protected List<String>order_by;
	protected List<String>group_by;
	protected String limit;
	protected List<Object[]>batchArgs;
	protected List<String>vSets;
	protected SQLMaker(){};
	private void init(){
		this.sb						= new StringBuilder();
		this.table_alias			= new HashMap<>();
		this.alias_entity			= new HashMap<>();
		this.columns				= new ArrayList<>();
		this.values					= new ArrayList<>();
		//select|update|delete
		this.where					= "1=1";
		this.andor					= new ArrayList<>();
		//select
		this.join_table_alias_on	= new ArrayList<>();
		this.order_by				= new ArrayList<>();
		this.group_by				= new ArrayList<>();
		this.limit					= "";
		//insert
		this.vSets					= new ArrayList<String>();
	}
	public SQLMaker clean(){
		this.init();
		this.batchArgs=null;
		return this;
	}
	/**清除排序字段*/
	public SQLMaker cleanOrderBy(){
		this.order_by=new ArrayList<String>();
		return this;
	}
	/**清除分组字段*/
	public SQLMaker cleanGroupBy(){
		this.group_by=new ArrayList<String>();
		return this;
	}
	/**清除查询字段*/
	public SQLMaker cleanSelect(){
		this.columns=new ArrayList<String>();
		return this;
	}
	/**获取 batchArgs*/
	public List<Object[]> getBatchArgs() {
		return batchArgs;
	}
	/**设置 batchArgs*/
	public SQLMaker setBatchArgs(List<Object[]> batchArgs) {
		this.batchArgs = batchArgs;
		return this;
	}
	public SQLMaker SELECT(String...columns){
		this.type=0;
		if(this.columns==null){
			this.init();
		}
		for(String column:columns){
			this.columns.add(column);
		}
		return this;
	}
	public SQLMaker SELECT_AS(String column,String alias){
		this.type=0;
		if(this.columns==null){
			this.init();
		}
		this.columns.add(StringUtil.format("? AS '?'", column,alias));
		return this;
	}
	public SQLMaker UPDATE(BaseEntity entity,String alias){
		this.type=1;
		this.init();
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(),alias);
		this.alias_entity.put(alias, entity);
		return this;
	}
	public SQLMaker DELETE(BaseEntity entity,String alias){
		this.type=2;
		this.init();
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(), alias);
		this.alias_entity.put(alias, entity);
		return this;
	}
	public SQLMaker INSERT_INTO(BaseEntity entity,String alias){
		this.type=3;
		this.init();
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(), alias);
		this.alias_entity.put(alias, entity);
		return this;
	}
	public SQLMaker CREATE_DATABASE(String schema_name){
		this.type=4;
		this.init();
		sb.append("CREATE DATABASE ").append(schema_name);
		return this;
	}
	public SQLMaker DEFAULT_CHARACTER_SET(String default_character_set){
		sb.append(" DEFAULT CHARACTER SET ").append(default_character_set);
		return this;
	}
	public SQLMaker COLLATE(String collate){
		sb.append(" COLLATE ").append(collate);
		return this;
	}
	public SQLMaker INTO_COLUMNS(String...columns){
		for(String column:columns){
			this.columns.add(column);
		}
		return this;
	}
	public SQLMaker INTO_VALUES(Object...values){
		for(Object value:values){
			this.values.add(value);
			this.vSets.add("?");
		}
		return this;
	}
	public SQLMaker FROM(BaseEntity entity,String alias){
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(),alias);
		this.alias_entity.put(alias, entity);
		return this;
	}
	public SQLMaker FROM_SELECT(SQLGod sm,String alias){
		Map<String, List<? extends Object>>map=sm.sql();
		for(String sql:map.keySet()){
			this.table_alias.put("("+sql+")", alias);
			List<? extends Object>values=map.get(sql);
			this.values.addAll(values);
			return this;
		}
		return this;
	}
	public SQLMaker SELECT_BEFORE_AS(String alias){
		String beforeSQL=sqlString();
		List<Object>beforeValues=new ArrayList<Object>();
		beforeValues.addAll(values);
		this.clean();
		this.table_alias.put("("+beforeSQL+")", alias);
		this.values.addAll(beforeValues);
		return this;
	}
	public SQLMaker LEFT_JOIN(BaseEntity entity,String alias,String on,Object...values){
		this.join_table_alias_on.add(" LEFT JOIN "+entity.tableName()+" "+alias+" ON "+on);
		this.alias_entity.put(alias, entity);
		for(Object value:values){
			this.values.add(value);
		}
		return this;
	}
	public SQLMaker RIGHT_JOIN(BaseEntity entity,String alias,String on,Object...values){
		this.join_table_alias_on.add(" RIGHT JOIN "+entity.tableName()+" "+alias+" ON "+on);
		this.alias_entity.put(alias, entity);
		for(Object value:values){
			this.values.add(value);
		}
		return this;
	}
	public SQLMaker WHERE(String str,Object...values){
		this.where=str;
		for(Object value:values){
			this.values.add(value);
		}
		return this;
	}
	public SQLMaker WHERE_IN(String str,Collection<? extends Object>valuse){
		StringBuilder sbu=new StringBuilder();
		List<String>vs=new ArrayList<String>();
		for(Object obj:valuse){
			this.values.add(obj);
			vs.add("?");
		}
		if(valuse.size()>0){
			this.where=sbu.append(str)
					.append(" IN (")
					.append(StringUtil.join(",",vs.toArray()))
					.append(" )")
					.toString();
		}else{
			this.where=sbu.append(str)
					.append(" IN ( '' )")
					.toString();
		}
		return this;
	}
	public SQLMaker AND(String str,Object...values){
		this.andor.add(" AND "+str);
		for(Object value:values){
			this.values.add(value);
		}
		return this;
	}
	public SQLMaker OR(String str,Object...values){
		this.andor.add(" OR "+str);
		for(Object value:values){
			this.values.add(value);
		}
		return this;
	}
	public SQLMaker SET(String str,Object...values){
		this.columns.add(str);
		for(Object value:values){
			this.values.add(value);
		}
		return this;
	}
	public SQLMaker ORDER_BY(String...columns){
		for(String column:columns){
			this.order_by.add(column);
		}
		return this;
	}
	public SQLMaker GROUP_BY(String...columns){
		for(String column:columns){
			this.group_by.add(column);
		}
		return this;
	}
	public abstract SQLMaker LIMIT(int page,int pageSize);
	
	private String sqlString(){
		if(this.type==0){
			
			sb.append("SELECT ").append(StringUtil.joinString(",", this.columns.toArray()));
			
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				tableNames.add(StringUtil.joinString(" ",tableName,alias));
			}
			sb.append(" FROM ").append(StringUtil.join(",", tableNames.toArray()));
			for(String join:this.join_table_alias_on){
				sb.append(join);
			}
			sb.append(" WHERE ").append(this.where);
			sb.append(StringUtil.join(" ", this.andor.toArray()));
			if(this.group_by.size()>0){
				sb.append(" GROUP BY ").append(StringUtil.join(",", this.group_by.toArray()));
			}
			if(this.order_by.size()>0){
				sb.append(" ORDER BY ").append(StringUtil.join(",", this.order_by.toArray()));
			}
			sb.append(limit);
		}else if(this.type==1){
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				tableNames.add(StringUtil.joinString(" ",tableName,alias));
			}
			sb.append("UPDATE ").append(StringUtil.join(",", tableNames.toArray()));
			sb.append(" SET ").append(StringUtil.join(",", this.columns.toArray()));
			sb.append(" WHERE ").append(this.where);
			sb.append(StringUtil.join(" ", this.andor.toArray()));
		}else if(this.type==2){
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				tableNames.add(tableName);
			}
			sb.append("DELETE FROM ").append(StringUtil.join(",", tableNames.toArray()))
			.append(" WHERE ").append(this.where)
			.append(StringUtil.join(" ", this.andor.toArray()));
		}else if(this.type==3){
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				BaseEntity entity=this.alias_entity.get(alias);
				tableNames.add(tableName);
				if(this.columns.size()==0){
					for(String p:entity.properties()){
						this.columns.add(entity.getPropertyTableColumn(p));
						this.vSets.add("?");
					}
				}
				if(this.values.size()==0){
					for(String p:entity.properties()){
						this.values.add(entity.getPropertyValue(p));
					}
				}
			}
			sb.append("INSERT INTO ").append(StringUtil.join(",",tableNames.toArray()))
			.append(" ( ").append(StringUtil.join(",", this.columns.toArray()))
			.append(" ) VALUES ( ").append(StringUtil.join(",",vSets.toArray())).append(" )");
		}else if(this.type==4){
			return sb.toString();
		}
		String sql=doSQLFilter(sb).toString();
		sb=new StringBuilder();
		return sql;
	}
	private String countSqlString(){
		StringBuilder stringBuilder=new StringBuilder();
		if(this.type==0){
			stringBuilder.append("SELECT COUNT(*) AS 'count'");
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				tableNames.add(StringUtil.joinString(" ",tableName,alias));
			}
			stringBuilder.append(" FROM ").append(StringUtil.join(",", tableNames.toArray()));
			for(String join:this.join_table_alias_on){
				stringBuilder.append(join);
			}
			stringBuilder.append(" WHERE ").append(this.where);
			stringBuilder.append(StringUtil.join(" ", this.andor.toArray()));
		}
		return doSQLFilter(stringBuilder).toString();
	}
	private StringBuilder doSQLFilter(StringBuilder stringBuilder){
		for(String alias:this.alias_entity.keySet()){
			StringBuffer sbf=new StringBuffer();
			Pattern pattern=Pattern.compile(alias+"\\.\\w+");
			BaseEntity entity=this.alias_entity.get(alias);
			Matcher matcher=pattern.matcher(stringBuilder.toString());
			
			while(matcher.find()){
				String pname=matcher.group().split("\\.")[1];
				if(this.type==2||this.type==3){
					matcher.appendReplacement(sbf, entity.getPropertyTableColumn(pname));
				}else{
					matcher.appendReplacement(sbf, alias+"."+entity.getPropertyTableColumn(pname));
				}
			}
			matcher.appendTail(sbf);
			stringBuilder=new StringBuilder(sbf);
		}
		return stringBuilder;
	}
	@Override
	public Map<String, List<? extends Object>> sql(){
		Map<String, List<? extends Object>>result=new HashMap<String, List<? extends Object>>();
		String sql=sqlString();
		result.put(sql, values);
		log.debug("SQLGod生成SQL::[{}]",result);
		return result;
	}
	@Override
	public Map<String, List<Object[]>> batchSql() {
		Map<String, List<Object[]>>result=new HashMap<String, List<Object[]>>();
		String sql=sqlString();
		result.put(sql, batchArgs);
		log.debug("SQLGod生成SQL::[{}]",result);
		return result;
	}
	@Override
	public Map<String, List<? extends Object>> countSql(){
		Map<String, List<? extends Object>>result=new HashMap<String, List<? extends Object>>();
		String sql=countSqlString();
		if(this.limit.length()>0){
			result.put(sql, values.subList(0, values.size()-2));
		}else{
			result.put(sql, values);
		}
		log.debug("SQLGod生成SQL::[{}]",result);
		return result;
	}
	@Override
	public String toString(){
		StringBuffer strb=new StringBuffer();
		strb.append(sqlString());
		strb.append(values);
		if(batchArgs!=null){
			for(Object[]args:batchArgs){
				strb.append(StringUtil.join(",", args));
			}
		}
		return strb.toString();
	}
}
