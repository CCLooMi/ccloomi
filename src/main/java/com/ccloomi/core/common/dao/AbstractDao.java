package com.ccloomi.core.common.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.component.sql.SQLGod;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.fasterxml.jackson.databind.ObjectMapper;

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

	public Object add(T entity) {
		return updateBySQLGod(new SQLMaker().INSERT_INTO((BaseEntity)entity, "#"));
	}

	public Object save(T entity) {
		return add(entity);
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
			ObjectMapper objMapper=new ObjectMapper();
			List<E>tList=new ArrayList<>();
			for(Map<String, Object>m:ls){
				tList.add(objMapper.convertValue(m, elementType));
			}
			return tList;
		}
		return new ArrayList<E>();
	}
}
