package com.ccloomi.web.dbManager.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.dbManager.bean.VisNetworkBean;
import com.ccloomi.web.dbManager.entity.SchemataEntity;

/**
 * 类    名：SchemataService
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年11月23日-上午11:05:52
 */
public interface SchemataService extends BaseService<SchemataEntity>{
	public VisNetworkBean findAsVisNetworkBySchemaName(String schemaName);
	/**
	 * 方法描述：查找字段和字段边列表
	 * 作        者：Chenxj
	 * 日        期：2015年11月24日-上午9:10:14
	 * @param schemaName
	 * @return
	 */
	public List<Map<String, Object>>findColumn2ColumnAsVisNetworkEdgesBySchemaName(String schemaName);
	/**
	 * 描述：分页查找数据库
	 * 作者：Chenxj
	 * 日期：2015年12月5日 - 下午11:43:39
	 * @param map
	 * @return
	 */
	public Map<String, Object>findSchematasByPage(@RequestBody Map<String, Object>map);
}
