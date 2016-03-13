package com.ccloomi.core.common.dao;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.component.sql.SQLGod;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.JSONUtil;

/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AbstractDao
 * 类 描 述：基础抽象类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月19日-下午4:17:54
 * @param <T>
 */
public abstract class AbstractDao<T extends BaseEntity> {
	protected final Logger log=LoggerFactory.getLogger(getClass());
	protected Class<T>entityClass;
	protected T _entity;
	protected String tableName;
	@Autowired
	protected JdbcTemplate jdbcTemplate;
	
	public AbstractDao (){
		this.entityClass=getEntityClass();
		Table table=entityClass.getAnnotation(Table.class);
		_entity=TEntity();
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
		return updateBySQLGod(SQLMakerFactory.getInstance().createMapker().INSERT_INTO(entity, "#"));
	}
	
	public int update(T entity) {
		String alias="et";
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.UPDATE(entity, alias);
		for(String p:entity.PVMap().keySet()){
			if(!"id".equals(p)){
				Object pv=entity.getPropertyValue(p);
				String ps=new StringBuilder().append(alias).append(".").append(p).append("=?").toString();
				sm.SET(ps,pv);
			}
		}
		sm.WHERE(alias+".id=?", entity.getPropertyValue(entity.getPropertyA(0)));
		return updateBySQLGod(sm);
	}
	/**
	 * 方法描述：懒更新，即不更新属性值为null的字段
	 * 作者：Chenxj
	 * 日期：2016年3月11日 - 下午1:18:12
	 * @param entity
	 * @return
	 */
	public int updateLazy(T entity){
		String alias="etlz";
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.UPDATE(entity, alias);
		//记录是否有更新字段
		int i=-1;
		for(String p:entity.PVMap().keySet()){
			if(!"id".equals(p)){
				Object pv=entity.getPropertyValue(p);
				if(pv!=null){
					String ps=new StringBuilder().append(alias).append(".").append(p).append("=?").toString();
					sm.SET(ps,pv);
					i++;
				}
			}
		}
		sm.WHERE(alias+".id=?", entity.getPropertyValue(entity.getPropertyA(0)));
		return i>0?updateBySQLGod(sm):0;
	}
	/**
	 * 方法描述：懒更新，updateLazy
	 * 作者：Chenxj
	 * 日期：2016年3月11日 - 下午1:17:45
	 * @param entity
	 * @return
	 */
	public int lazyUpdate(T entity){
		return updateLazy(entity);
	}
	/**
	 * 方法描述：更新map指定的字段不管字段值是否为null,
	 * 这样可以直接将从客户端接收到的map数据直接进行更新数据库操作
	 * 作者：Chenxj
	 * 日期：2016年3月11日 - 下午3:53:29
	 * @param map
	 * @return
	 */
	public int updateByMap(Map<String, ? extends Object>map){
		Map<String,Object>m=formateMapByEntity(map);
		String alias="etlz";
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.UPDATE(_entity, alias);
		//记录是否有更新字段
		int i=-1;
		for(String p:m.keySet()){
			if(!"id".equals(p)){
				Object pv=m.get(p);
				if(pv!=null){
					String ps=new StringBuilder().append(alias).append(".").append(p).append("=?").toString();
					sm.SET(ps,pv);
					i++;
				}
			}
		}
		sm.WHERE(alias+".id=?", m.get("id"));
		return i>0?updateBySQLGod(sm):0;
	}
	/**
	 * 方法描述：格式化map,
	 * 从map中取出entity中有的属性和值到新map中
	 * 防止updateByMap出错
	 * 作者：Chenxj
	 * 日期：2016年3月11日 - 下午4:04:14
	 */
	private Map<String,Object> formateMapByEntity(Map<String, ? extends Object>map){
		Map<String, Object>m=new HashMap<>();
		_entity.prepareProperties();
		List<String>ps=_entity.properties();
		for(String p:ps){
			if(map.containsKey(p)){
				m.put(p, map.get(p));
			}
		}
		return m;
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
	public List<T> getByIds(Collection<? extends Object>ids){
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("*")
		.FROM(TEntity(), "#")
		.WHERE_IN("#.id", ids);
		return findBySQLGod(sm, entityClass);
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
			_entity.prepareProperties();
			List<String>properties=_entity.properties();
			for(T t:entities){
				BaseEntity bt=(BaseEntity) t;
				//需要获取T内属性值
				bt.prepareProperties();
				List<Object>args=new ArrayList<Object>();
				for(String p:properties){
					args.add(bt.PVMap().get(p));
				}
				batchArgs.add(args.toArray());
			}
			SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
			sm.INSERT_INTO(_entity, "#");
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
				tList.add(JSONUtil.convertMapToBean(m, elementType));
			}
			return tList;
		}
		return new ArrayList<E>();
	}
	public List<T>findAll(){
		return findBySQLGod(SQLMakerFactory.getInstance().createMapker().SELECT("*").FROM(_entity, "#"), getEntityClass());
	}
}
