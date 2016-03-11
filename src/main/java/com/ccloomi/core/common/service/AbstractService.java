package com.ccloomi.core.common.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.component.sql.SQLGod;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AbstractService
 * 类 描 述：基础服务抽象类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午9:58:54
 * @param <T>
 */
public abstract class AbstractService<T extends BaseEntity> {
	protected final Logger log=LoggerFactory.getLogger(getClass());
	
	@Autowired
	protected BaseDao<T> baseDao;
	
	public int add(T entity) {
		return baseDao.add(entity);
	}

	public Object save(T entity) {
		return baseDao.save(entity);
	}

	public int update(T entity) {
		return baseDao.update(entity);
	}
	public int updateLazy(T entity) {
		return baseDao.updateLazy(entity);
	}
	public int lazyUpdate(T entity) {
		return baseDao.lazyUpdate(entity);
	}
	public int updateByMap(Map<String, ? extends Object> map) {
		return baseDao.updateByMap(map);
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
	public int[] batchSave(Collection<T>entities){
		return baseDao.batchSave(entities);
	}
	public int[] batchDelete(Collection<? extends Object> ids) {
		return baseDao.batchDelete(ids);
	}
	public List<T>findAll(){
		return baseDao.findAll();
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
	/**
	 * 方法描述：分页查询
	 * 作        者：Chenxj
	 * 日        期：2015年12月15日-上午9:28:55
	 * @param map
	 * @return
	 */
	public Map<String, Object> byPage(Map<String, Object>map,ByPageSelect byPageSelect){
		return byPage(map,null,byPageSelect);
	}
	/**
	 * 方法描述：分页查询（可指定元素类型）
	 * 作        者：Chenxj
	 * 日        期：2015年12月15日-上午10:07:53
	 * @param map
	 * @param select
	 * @param elementType
	 * @return
	 */
	public <E> Map<String, Object> byPage(Map<String, Object> map,Class<E> elementType,ByPageSelect byPageSelect) {
		Map<String, Object>rm=new HashMap<>();
		int page=-1+(int) map.get("pageNumber");
		int pageSize=(int) map.get("pageSize");
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		byPageSelect.doSelect(sm, map);
		sm.LIMIT(page, pageSize);
		if(page==0){
			rm.put("totalNumber", baseDao.countBySQLGod(sm));
		}
		if(elementType!=null){
			List<E>ls=baseDao.findBySQLGod(sm,elementType);
			rm.put("data", ls);
		}else{
			List<Map<String, Object>>ls=baseDao.findBySQLGod(sm);
			rm.put("data", ls);
		}
		return rm;
	}
	/**
	 * 方法描述：分页查询（移动端）
	 * 作        者：Chenxj
	 * 日        期：2016年1月13日-下午1:30:28
	 * @param map
	 * @param byPageSelect
	 * @return
	 */
	public List<Map<String, Object>> byPageWithoutTotalNumber(Map<String, Object>map,ByPageSelect byPageSelect){
		List<Map<String, Object>>ls=new ArrayList<>();
		int page=(int)map.get("page");
		int pageSize=10;
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		byPageSelect.doSelect(sm, map);
		processConditions(sm, map);
		sm.LIMIT(page*pageSize, pageSize);
		ls=baseDao.findBySQLGod(sm);
		return ls;
	}
	/**
	 * 方法描述：分页查询（移动端）
	 * 作        者：Chenxj
	 * 日        期：2016年1月13日-下午1:30:14
	 * @param map
	 * @param elementType
	 * @param byPageSelect
	 * @return
	 */
	public <E> List<E> byPageWithoutTotalNumber(Map<String, Object> map,Class<E> elementType,ByPageSelect byPageSelect) {
		List<E>ls=new ArrayList<>();
		int page=(int)map.get("page");
		int pageSize=10;
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		byPageSelect.doSelect(sm, map);
		processConditions(sm, map);
		sm.LIMIT(page*pageSize, pageSize);
		ls=baseDao.findBySQLGod(sm,elementType);
		return ls;
	}
	private void processConditions(SQLMaker sm,Map<String,Object>map){
		if(map.get("keyword")!=null||map.get("filterBy")!=null||map.get("orderBy")!=null){
			//需要在select before之前执行
			List<String>columns=sm.getColumns();
			sm.SELECT_BEFORE_AS("temp").SELECT("*");
			if(map.get("keyword")!=null){
				StringBuilder sb=new StringBuilder("(");
				int i=0;
				for(String column:columns){
					//column 格式例子:: a.name;a.name AS 'name';a.name,a.age
					if(i!=0)sb.append(" OR");
					sb.append(" temp.").append(column).append(" LIKE '%").append(map.get("keyword")).append("%'");
					i++;
				}
				sb.append(")");
				sm.AND(sb.toString());
			}
			if(map.get("filterBy")!=null){
				@SuppressWarnings("unchecked")
				Map<String, Object>filterByMap=(Map<String, Object>) map.get("filterBy");
				for(String filterBy:filterByMap.keySet()){
					sm.AND("temp."+filterBy+"=?", filterByMap.get(filterBy));
				}
			}
			if(map.get("orderBy")!=null){
				@SuppressWarnings("unchecked")
				List<String>orderByList=(List<String>) map.get("orderBy");
				for(String orderBy:orderByList){
					sm.ORDER_BY("temp."+orderBy);
				}
			}
		}//end if
	}
}
