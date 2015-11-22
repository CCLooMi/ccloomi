package com.ccloomi.web.dbManager.service;

import java.util.List;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.dbManager.entity.TablesEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：TablesService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午4:08:18
 */
public interface TablesService extends BaseService<TablesEntity>{
	public List<TablesEntity>findTablesByTableSchema(String tableSchema);
}
