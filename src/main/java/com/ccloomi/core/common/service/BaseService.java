package com.ccloomi.core.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLGod;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：BaseService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午9:32:35
 * @param <T>
 */
public interface BaseService<T> {
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
	public List<T>						findAll				();
	public Map<String, Object>			byPage				(Map<String, Object>map,ByPageSelect byPageSelect);
	public <E>Map<String, Object>		byPage				(Map<String, Object>map,Class<E>elementType,ByPageSelect byPageSelect);
	//分页(适用于移动端)
	public List<Map<String, Object>>	byPageWithoutTotalNumber	(Map<String, Object>map,ByPageSelect byPageSelect);
	public <E>List<E>					byPageWithoutTotalNumber	(Map<String, Object>map,Class<E>elementType,ByPageSelect byPageSelect);
}
