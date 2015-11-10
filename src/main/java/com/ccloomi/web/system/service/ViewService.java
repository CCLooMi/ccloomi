package com.ccloomi.web.system.service;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.ViewEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ViewService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午10:58:19
 */
public interface ViewService extends BaseService<ViewEntity>{
	public Map<String, Object> findViewsByPage(Map<String, Object>map);
	/**
	 * 描述：通过角色ID查找视图树列表，包含has字段(1:有,0:无)
	 * 作者：Chenxj
	 * 日期：2015年11月10日 - 下午10:03:33
	 * @param idRole
	 * @return
	 */
	public List<Map<String, Object>>findViewsTreeByRoleId(Object idRole);
}
