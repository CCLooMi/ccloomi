package com.ccloomi.core.common.dao;

import java.util.Collection;
import java.util.List;
import java.util.Map;

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
public interface BaseDao<T> {
	public int 							add					(T entity);
	public Object 						save				(T entity);
	public int 							update				(T entity);
	public int 							delete				(Object id);
	public T 							getById				(Object id);
	public long							countBySQLGod		(SQLGod sg);
	public int 							updateBySQLGod		(SQLGod sg);
	public int[] 						batchUpdateBySQLGod	(SQLGod sg);
	public List<Map<String, Object>>	findBySQLGod		(SQLGod sg);
	public int[]						batchDelete			(Object...ids);
	public int[]						batchSave			(Collection<T>entities);
	public <E>List<E> 					findBySQLGod		(SQLGod sg,Class<E>elementType);
	public int[]						batchDelete			(Collection<? extends Object>ids);
}
