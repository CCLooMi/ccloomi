package com.ccloomi.core.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.component.sql.SQLGod;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：BaseDao
 * 类 描 述：所有Dao接口都应该基础Dao接口
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午9:59:27
 * @param <T>
 */
public interface BaseDao<T extends BaseEntity> {
	public int 							add					(T entity);
	public Object 						save				(T entity);
	public int 							update				(T entity);
	public int							updateLazy			(T entity);
	public int							lazyUpdate			(T entity);
	public int							updateByMap			(Map<String, ? extends Object>map);
	public int 							delete				(Object id);
	public int 							updateBySQLGod		(SQLGod sg);
	public int[] 						batchUpdateBySQLGod	(SQLGod sg);
	public int[]						batchDelete			(Object...ids);
	public int[]						batchSave			(Collection<T>entities);
	public int[]						batchDelete			(Collection<? extends Object>ids);
	
	public T 							getById				(Object id);
	public long							countBySQLGod		(SQLGod sg);
	public List<Map<String, Object>>	findBySQLGod		(SQLGod sg);
	public List<T>						findAll				();
	public List<T>						getByIds			(Collection<? extends Object>ids);
	public <E>List<E> 					findBySQLGod		(SQLGod sg,Class<E>elementType);
	
	public Map<String, Object>			findAsMap				(Object id);
	public List<Map<String, Object>>	findAsListMap			(Collection<? extends Object>ids);
	public <id>List<Map<String, Object>>findAsListMap			(@SuppressWarnings("unchecked") id...ids);
	public T 							findAsEntity			(Object id);
	public List<T> 						findAsListEntity		(Collection<? extends Object>ids);
	public <id>List<T> 					findAsListEntity		(@SuppressWarnings("unchecked") id...ids);
	public void							cacheMap				(Map<String, ? extends Object>map);
	public void							cacheListMap			(List<Map<String, Object>>maps);
	public void							cacheEntity				(T entity);
	public void							cacheListEntity			(List<T>entities);
	public void							uncache					(Object id);
	public void							uncacheList				(Collection<? extends Object>ids);
	public <id>void						uncacheList				(@SuppressWarnings("unchecked") id...ids);
	public void							recacheMap				(Map<String, ? extends Object>map);
	public void							recacheListMap			(List<Map<String, Object>>maps);
	public void							recacheEntity			(T entity);
	public void							recacheListEntity		(List<T>entities);
}
