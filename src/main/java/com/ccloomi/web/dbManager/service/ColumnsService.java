package com.ccloomi.web.dbManager.service;

import java.util.List;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.dbManager.entity.ColumnsEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ColumnsService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午4:06:11
 */
public interface ColumnsService extends BaseService<ColumnsEntity>{
	public List<ColumnsEntity>findColumnsByTableSchemaAndTableName(String tableSchema,String tableName);
}
