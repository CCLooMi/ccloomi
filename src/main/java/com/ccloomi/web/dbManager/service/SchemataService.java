package com.ccloomi.web.dbManager.service;

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
}
