package com.ccloomi.core.common.dao;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.component.sql.SQLGod;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.core.util.StringUtil;

/**深圳市设计同道技术有限公司
 * 类    名：AbstractDao
 * 类描述：基础Dao抽象类
 * 作    者：Chenxj
 * 日    期：2015年10月19日-下午4:17:54
 * @param <E>
 */
public abstract class AbstractDao<T> {
	protected final Logger log=LoggerFactory.getLogger(getClass());
	protected Class<T>entityClass;
	protected String tableName;
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public AbstractDao (){
		this.entityClass=getEntityClass();
		Table table=entityClass.getAnnotation(Table.class);
		this.tableName=(table==null?entityClass.getName():table.name());
	}
	
	/**
	 * 方法描述：获取T的Class
	 * 作者：Chenxj
	 * 日期：2015年7月6日 - 上午11:25:00
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getEntityClass(){
		return (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**
	 * 描述：获取T的实例
	 * 作者：Chenxj
	 * 日期：2015年11月10日 - 下午10:35:25
	 * @return
	 */
	private T TEntity(){
		try {
			return getEntityClass().newInstance();
		} catch (Exception e) {
			log.error("获取T实例异常", e);
			return null;
		}
	}
	public int add(T entity) {
		return updateBySQLGod(new SQLMaker().INSERT_INTO((BaseEntity)entity, "#"));
	}
	
	public int update(T entity) {
		BaseEntity _bt=(BaseEntity) entity;
		String alias="et";
		SQLMaker sm=new SQLMaker();
		sm.UPDATE(_bt, alias);
		for(String p:_bt.getPVMap().keySet()){
			Object pv=_bt.getPropertyValue(p);
			String ps=new StringBuilder().append(alias).append(".").append(p).append("=?").toString();
			sm.SET(ps,pv);
		}
		sm.WHERE(alias+".id=?", _bt.getPropertyValue(_bt.getPropertyA(0)));
		return updateBySQLGod(sm);
	}

	public int delete(Object id) {
		if(id!=null){
			String sql="DELETE FROM ? WHERE id=?".replaceFirst("\\?", tableName);
			return jdbcTemplate.update(sql, id);
		}
		return 0;
	}

	public T getById(Object id) {
		if(id!=null){
			String sql="SELECT * FROM ? WHERE id=?".replaceFirst("\\?", tableName);
			List<T>tList=jdbcTemplate.queryForList(sql, entityClass, id);
			if(tList!=null&&tList.size()>0){
				return tList.get(0);
			}
		}
		return null;
	}	

	public int[] batchDelete(Object... ids) {
		int[]r=new int[ids.length];
		if(ids.length>0){
			String sql="DELETE FROM ? WHERE id=?".replaceFirst("\\?", tableName);
			List<Object[]>batchArgs=new ArrayList<Object[]>();
			for(Object id:ids){
				Object[]objs={id};
				batchArgs.add(objs);
			}
			r=jdbcTemplate.batchUpdate(sql, batchArgs);
		}
		return r;
	}
	public int[] batchSave(Collection<T>entities){
		int[]r=new int[entities==null?0:entities.size()];
		if(entities!=null&&entities.size()>0){
			List<Object[]>batchArgs=new ArrayList<Object[]>();
			List<String>properties=null;
			BaseEntity firstBt=null;
			for(T t:entities){
				BaseEntity bt=(BaseEntity) t;
				bt.prepareProperties();
				if(properties==null){
					firstBt=bt;
					properties=firstBt.properties();
				}
				List<Object>args=new ArrayList<Object>();
				for(String p:properties){
					args.add(bt.getPVMap().get(p));
				}
				batchArgs.add(args.toArray());
			}
			firstBt=(BaseEntity) TEntity();
			if(firstBt==null){
				return r;
			}
			SQLMaker sm=new SQLMaker();
			sm.INSERT_INTO(firstBt, "#");
			sm.setBatchArgs(batchArgs);
			return batchUpdateBySQLGod(sm);
		}
		return r;
	}
	public int[] batchDelete(Collection<? extends Object> ids) {
		int[]r=new int[ids==null?0:ids.size()];
		if(ids!=null&&ids.size()>0){
			String sql="DELETE FROM ? WHERE id=?".replaceFirst("\\?", tableName);
			List<Object[]>batchArgs=new ArrayList<Object[]>();
			for(Object id:ids){
				Object[]objs={id};
				batchArgs.add(objs);
			}
			r=jdbcTemplate.batchUpdate(sql, batchArgs);
		}
		return r;
	}
	public long countBySQLGod(SQLGod sg){
		Map<String, List<? extends Object>>map=sg.countSql();
		for(String sql:map.keySet()){
			List<Map<String, Object>>ls=jdbcTemplate.queryForList(sql, map.get(sql).toArray());
			if(ls.size()>0){
				return (long) ls.get(0).get("count");
			}
		}
		return 0;
	}
	public int updateBySQLGod(SQLGod sg) {
		Map<String, List<? extends Object>>map=sg.sql();
		for(String sql:map.keySet()){
			return jdbcTemplate.update(sql, map.get(sql).toArray());
		}
		return 0;
	}

	public int[] batchUpdateBySQLGod(SQLGod sg) {
		Map<String,List<Object[]>>map=sg.batchSql();
		for(String sql:map.keySet()){
			return jdbcTemplate.batchUpdate(sql, map.get(sql));
		}
		return new int[0];
	}

	public List<Map<String, Object>> findBySQLGod(SQLGod sg) {
		Map<String, List<? extends Object>>map=sg.sql();
		for(String sql:map.keySet()){
			return jdbcTemplate.queryForList(sql, map.get(sql).toArray());
		}
		return new ArrayList<Map<String,Object>>();
	}
	public <E> List<E> findBySQLGod(SQLGod sg,Class<E>elementType) {
		Map<String, List<? extends Object>>map=sg.sql();
		for(String sql:map.keySet()){
			List<Map<String, Object>>ls=jdbcTemplate.queryForList(sql,map.get(sql).toArray());
			List<E>tList=new ArrayList<>();
			for(Map<String, Object>m:ls){
				tList.add(convertMap(m, elementType));
			}
			return tList;
		}
		return new ArrayList<E>();
	}
	private <E> E convertMap(Map<String,? extends Object>map,Class<E>elementType){
		try {
			E ebj=elementType.newInstance();
			if(ebj instanceof BaseEntity){
				BaseEntity b=(BaseEntity) ebj;
				b.prepareProperties();
				for(String p:b.properties()){
					Field field=elementType.getDeclaredField(p);
					field.setAccessible(true);
					field.set(b, map.get(b.getPropertyTableColumn(p)));
				}
			}else{
				for(Field field:elementType.getDeclaredFields()){
					String columnName=field.getName();
					String get="get"+StringUtil.upperCaseFirstLatter(columnName);
					Method getMethod=elementType.getDeclaredMethod(get);
					Column c=getMethod.getDeclaredAnnotation(Column.class);
					if(c!=null){
						columnName=c.name();
					}
					if(map.get(columnName)!=null){
						field.setAccessible(true);
						field.set(ebj, map.get(columnName));
					}
				}
			}
			return ebj;
		}catch(Exception e){
			log.error("转换出现异常::", e);
		}
		return null;
	}
}
