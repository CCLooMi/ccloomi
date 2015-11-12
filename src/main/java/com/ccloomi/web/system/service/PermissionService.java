package com.ccloomi.web.system.service;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.PermissionEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：PermissionService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午11:04:22
 */
public interface PermissionService extends BaseService<PermissionEntity>{
	public List<Map<String, Object>>findpermissionsTreeByRoleId(Object idRole);
	public List<String>findpermissionIdsByRoleId(Object idRole);
}
