package com.sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.util.StringUtil;
import com.sql.bean.ValuePair;
import com.sql.entity.BaseEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SQLMaker
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-下午12:54:25
 */
public abstract class SQLMaker implements SQLGod,Maker{
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
	protected List<String>order_by;
	protected List<String>group_by;
	protected String limit;
	
	protected List<Object>values;
	protected List<Object[]>batchArgs;
	protected List<String>vSets;
	protected SQLMaker(){};
	//用户SQL修复
	private Map<String, ValuePair>aliasValuePairMap;
	private Map<String, String>aliasToAlias;
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
		this.aliasValuePairMap		= new HashMap<>();
		this.aliasToAlias			= new HashMap<>();
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
	/**
	 * 描述：添加值
	 * 作者：Chenxj
	 * 日期：2016年2月20日 - 下午2:14:03
	 * @param values
	 * @return
	 */
	public SQLMaker addValues(Collection<? extends Object>values){
		this.values.addAll(values);
		return this;
	}
	/**
	 * 描述：添加值
	 * 作者：Chenxj
	 * 日期：2016年2月20日 - 下午2:14:08
	 * @param values
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <value>SQLMaker addValues(value...values){
		for(Object v:values){
			this.values.add(v);
		}
		return this;
	}
	
	@Override
	public Maker SELECT(String... columns) {
		if(this.columns==null){
			this.type=0;
			this.init();
		}
		for(String column:columns){
			this.columns.add(column);
		}
		return this;
	}

	@Override
	public Maker SELECT_AS(String column, String alias) {
		if(this.columns==null){
			this.type=0;
			this.init();
		}
		this.columns.add(StringUtil.format("? AS '?'", column,alias));
		return this;
	}

	@Override
	public Maker UPDATE(BaseEntity entity, String alias) {
		this.type=1;
		this.init();
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(),alias);
		this.alias_entity.put(alias, entity);
		return this;
	}

	@Override
	public Maker DELETE(BaseEntity entity, String alias) {
		this.type=2;
		this.init();
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(), alias);
		this.alias_entity.put(alias, entity);
		return this;
	}

	@Override
	public Maker INSERT_INTO(BaseEntity entity, String alias) {
		this.type=3;
		this.init();
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(), alias);
		this.alias_entity.put(alias, entity);
		return this;
	}

	@Override
	public Maker CREATE_DATABASE(String schema_name) {
		this.type=4;
		this.init();
		sb.insert(0, InsertBeforeSQL());
		sb.append("CREATE DATABASE ").append(schema_name);
		return this;
	}

	@Override
	public Maker DEFAULT_CHARACTER_SET(String default_character_set) {
		sb.append(" DEFAULT CHARACTER SET ").append(default_character_set);
		return this;
	}

	@Override
	public Maker COLLATE(String collate) {
		sb.append(" COLLATE ").append(collate);
		return this;
	}

	@Override
	public Maker INTO_COLUMNS(String... columns) {
		for(String column:columns){
			this.columns.add(column);
			this.vSets.add("?");
		}
		return this;
	}

	@Override
	public Maker INTO_COLUMNS(Collection<? extends Object> columns) {
		for(Object column:columns){
			this.columns.add(String.valueOf(column));
			this.vSets.add("?");
		}
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker INTO_VALUES(value... values) {
		this.addValues(values);
		return this;
	}

	@Override
	public Maker FROM(BaseEntity entity, String alias) {
		entity.prepareProperties();
		this.table_alias.put(entity.tableName(),alias);
		this.alias_entity.put(alias, entity);
		return this;
	}

	@Override
	public Maker FROM_SELECT(SQLGod sm, String alias) {
		Map<String, List<? extends Object>>map=sm.sql();
		for(String sql:map.keySet()){
			this.table_alias.put("("+sql+")", alias);
			List<? extends Object>values=map.get(sql);
			this.values.addAll(values);
			return this;
		}
		return this;
	}

	@Override
	public Maker SELECT_BEFORE_AS(String alias) {
		String beforeSQL=sqlString();
		List<Object>beforeValues=new ArrayList<Object>();
		beforeValues.addAll(values);
		this.clean();
		this.table_alias.put("("+beforeSQL+")", alias);
		this.values.addAll(beforeValues);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker LEFT_JOIN(BaseEntity entity, String alias, String on, value... values) {
		this.join_table_alias_on.add(" LEFT JOIN "+entity.tableName()+" "+alias+" ON "+on);
		this.alias_entity.put(alias, entity);
		this.addValues(values);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker RIGHT_JOIN(BaseEntity entity, String alias, String on, value... values) {
		this.join_table_alias_on.add(" RIGHT JOIN "+entity.tableName()+" "+alias+" ON "+on);
		this.alias_entity.put(alias, entity);
		this.addValues(values);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker WHERE(String str, value... values) {
		this.where=str;
		this.addValues(values);
		return this;
	}

	@Override
	public Maker WHERE_IN(String str, Collection<? extends Object> values) {
		return WHERE_IN(str, values.toArray());
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker WHERE_IN(String str, value... values) {
		StringBuilder sbu=new StringBuilder();
		List<String>vs=new ArrayList<String>();
		for(Object obj:values){
			this.values.add(obj);
			vs.add("?");
		}
		if(values.length>0){
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

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker AND(String str, value... values) {
		this.andor.add(" AND "+str);
		this.addValues(values);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker OR(String str, value... values) {
		this.andor.add(" OR "+str);
		this.addValues(values);
		return this;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <value> Maker SET(String str, value... values) {
		this.columns.add(str);
		this.addValues(values);
		return this;
	}

	@Override
	public Maker ORDER_BY(String... columns) {
		for(String column:columns){
			this.order_by.add(column);
		}
		return this;
	}

	@Override
	public Maker GROUP_BY(String... columns) {
		for(String column:columns){
			this.group_by.add(column);
		}
		return this;
	}
	@Override
	public String InsertBeforeSQL() {
		return "";
	}

	@Override
	public String InsertAfterSQL() {
		return "";
	}
	/**
	 * 描述：ss.loginName=? AND ss.id=? => T1.loginName=? AND ss.id=?
	 * 作者：Chenxj
	 * 日期：2016年8月2日 - 下午8:51:01
	 * @param fixStr
	 * @param a 原始别名
	 * @param b 真实别名
	 * @param vp 属性和table字段的键对
	 * @return 返回正确的sql语句
	 */
	private String fixSQL(String fixStr,String a,String b,ValuePair vp){
		for(Object p:vp.getLeftAll()){
			fixStr=fixStr.replaceAll(a+"."+p, b+"."+vp.getLeft(p));
		}
		for(Object p:vp.getRightAll()){
			fixStr=fixStr.replaceAll(a+"."+p, b+"."+vp.getLeft(p));
		}
		return fixStr;
	}
	private String sqlString(){
		StringBuilder sql=new StringBuilder();
		sql.insert(0, InsertBeforeSQL());
		if(this.type==0){
			//查询
			sql.append("SELECT ").append(StringUtil.joinString(",", this.columns.toArray()));
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				tableNames.add(StringUtil.joinString(" ",tableName,alias));
			}
			sql.append(" FROM ").append(StringUtil.join(",", tableNames.toArray()));
			for(String alias:this.alias_entity.keySet()){
				BaseEntity entity=this.alias_entity.get(alias);
				if(entity.hasExtendType()){
					//TODO
					Map<String, ValuePair>mapping=entity.getMapping();
					Set<String>tables=mapping.keySet();
					if(tables.size()>2){
						//过滤掉第一个和最后一个
						int i=0;
						for(String table:tables){
							if(i!=0&&i<(tables.size()-1)){
								this.join_table_alias_on.add(" LEFT JOIN "+table+" T"+i+" ON T"+i+".id="+alias+".id");
								this.aliasValuePairMap.put("T"+i, mapping.get(table));
								this.aliasToAlias.put("T"+i, alias);
							}
							i++;
						}
					}
				}
			}
			for(String join:this.join_table_alias_on){
				sql.append(join);
			}
			sql.append(" WHERE ").append(this.where);
			sql.append(StringUtil.join(" ", this.andor.toArray()));
			if(this.group_by.size()>0){
				sql.append(" GROUP BY ").append(StringUtil.join(",", this.group_by.toArray()));
			}
			if(this.order_by.size()>0){
				sql.append(" ORDER BY ").append(StringUtil.join(",", this.order_by.toArray()));
			}
			sql.append(limit);
		}else if(this.type==1){
			//更新
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				tableNames.add(StringUtil.joinString(" ",tableName,alias));
			}
			sql.append("UPDATE ").append(StringUtil.join(",", tableNames.toArray()));
			sql.append(" SET ").append(StringUtil.join(",", this.columns.toArray()));
			sql.append(" WHERE ").append(this.where);
			sql.append(StringUtil.join(" ", this.andor.toArray()));
		}else if(this.type==2){
			//删除
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				tableNames.add(tableName);
			}
			sql.append("DELETE FROM ").append(StringUtil.join(",", tableNames.toArray()))
			.append(" WHERE ").append(this.where)
			.append(StringUtil.join(" ", this.andor.toArray()));
		}else if(this.type==3){
			//插入
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				BaseEntity entity=this.alias_entity.get(alias);
				tableNames.add(tableName);
				if(this.columns.size()==0){
					ValuePair cvp=entity.getCommonValuePair();
					Set<Object>commons=cvp.getLeftAll();
					for(Object p:commons){
						this.columns.add(cvp.getLeft(p));
						this.vSets.add("?");
					}
					ValuePair tvp=entity.getTableValuePair(tableName);
					Set<Object>ts=tvp.getLeftAll();
					for(Object p:ts){
						this.columns.add(tvp.getLeft(p));
						this.vSets.add("?");
					}
				}
				if(this.values.size()==0){
					ValuePair cvp=entity.getCommonValuePair();
					Set<Object>commons=cvp.getLeftAll();
					for(Object p:commons){
						this.values.add(entity.getPropertyValue(p));
					}
					ValuePair tvp=entity.getTableValuePair(tableName);
					Set<Object>ts=tvp.getLeftAll();
					for(Object p:ts){
						this.values.add(entity.getPropertyValue(p));
					}
				}
			}
			sql.append("INSERT INTO ").append(StringUtil.join(",",tableNames.toArray()))
			.append(" ( ").append(StringUtil.join(",", this.columns.toArray()))
			.append(" ) VALUES ( ").append(StringUtil.join(",",vSets.toArray())).append(" )");
		}else if(this.type==4){
			//创建数据库
			sb.append(InsertAfterSQL());
			return sb.toString();
		}
		sql.append(InsertAfterSQL());
		return doSQLFilter(sql).toString();
	}
	private String countSqlString(){
		StringBuilder sql=new StringBuilder();
		if(this.type==0){
			sql.append("SELECT COUNT(*) AS 'count'");
			List<String>tableNames=new ArrayList<String>();
			for(String tableName:this.table_alias.keySet()){
				String alias=this.table_alias.get(tableName);
				tableNames.add(StringUtil.joinString(" ",tableName,alias));
			}
			sql.append(" FROM ").append(StringUtil.join(",", tableNames.toArray()));
			for(String join:this.join_table_alias_on){
				sql.append(join);
			}
			sql.append(" WHERE ").append(this.where);
			sql.append(StringUtil.join(" ", this.andor.toArray()));
		}
		return doSQLFilter(sql).toString();
	}
	private StringBuilder doSQLFilter(StringBuilder sql){
		for(String alias:this.alias_entity.keySet()){
			StringBuffer sbf=new StringBuffer();
			Pattern pattern=Pattern.compile(alias+"\\.\\w+");
			BaseEntity entity=this.alias_entity.get(alias);
			Matcher matcher=pattern.matcher(sql.toString());
			ValuePair vp=entity.getMapping().get(entity.tableName());
			while(matcher.find()){
				String pname=matcher.group().split("\\.")[1];
				if(this.type==2||this.type==3){
					matcher.appendReplacement(sbf, vp.getLeft(pname));
				}else{
					matcher.appendReplacement(sbf, alias+"."+vp.getLeft(pname));
				}
			}
			matcher.appendTail(sbf);
			sql=new StringBuilder(sbf);
		}
		String sq=sql.toString();
		for(String ala:this.aliasValuePairMap.keySet()){
			ValuePair vpr=this.aliasValuePairMap.get(ala);
			sq=fixSQL(sq, this.aliasToAlias.get(ala), ala, vpr);
		}
		return new StringBuilder(sq);
	}
	
	@Override
	public Map<String, List<? extends Object>> sql() {
		Map<String, List<? extends Object>>result=new HashMap<String, List<? extends Object>>();
		//TODO
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
	public Map<String, List<? extends Object>> countSql() {
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
