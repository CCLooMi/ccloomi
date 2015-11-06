package com.ccloomi.core.common.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.core.component.sql.SQLGod;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AbstractService
 * 类 描 述：基础服务抽象类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午9:58:54
 * @param <T>
 */
public abstract class AbstractService<T> {
	protected final Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected BaseDao<T> baseDao;
	
	public Object add(T entity) {
		return baseDao.add(entity);
	}

	public Object save(T entity) {
		return baseDao.save(entity);
	}

	public int update(T entity) {
		return baseDao.update(entity);
	}

	public int delete(Object id) {
		return baseDao.delete(id);
	}

	public T getById(Object id) {
		return baseDao.getById(id);
	}
	
	public int[] batchDelete(Object... ids) {
		return baseDao.batchDelete(ids);
	}
	
	public int[] batchDelete(Collection<? extends Object> ids) {
		return baseDao.batchDelete(ids);
	}

	public long countBySQLGod(SQLGod sg){
		return baseDao.countBySQLGod(sg);
	}
	
	public int updateBySQLGod(SQLGod sg) {
		return baseDao.updateBySQLGod(sg);
	}

	public int[] batchUpdateBySQLGod(SQLGod sg) {
		return baseDao.batchUpdateBySQLGod(sg);
	}

	public List<Map<String, Object>> findBySQLGod(SQLGod sg) {
		return baseDao.findBySQLGod(sg);
	}

	public <E>List<E> findBySQLGod(SQLGod sg, Class<E> elementType) {
		return baseDao.findBySQLGod(sg, elementType);
	}
}
