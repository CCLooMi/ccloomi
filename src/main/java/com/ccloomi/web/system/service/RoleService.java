package com.ccloomi.web.system.service;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.PermissionEntity;
import com.ccloomi.web.system.entity.RoleEntity;
import com.ccloomi.web.system.entity.ViewEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月4日-上午8:55:14
 */
public interface RoleService extends BaseService<RoleEntity>{
	public Map<String, Object>findRolesByPage(Map<String, Object>map);
	public List<ViewEntity> findViewsByIdUser(Object idUser);
	public List<PermissionEntity> findPermissionsByIdUser(Object idUser);
	public List<RoleEntity> findRolesByIdUser(Object idUser);
	public List<RoleEntity> findRolesByIdTarget(Object idProduct);
	public boolean saveViewJstreeData(Map<String, Object>map);
	public boolean savePermissionJstreeData(Map<String, Object>map);
	/**描述：分页查找角色下的所有用户
	 * 作者：Chenxj
	 * 日期：2015年11月14日 - 下午2:11:13
	 * @param map
	 * @return
	 */
	public Map<String, Object> findUsersInRoleByPage(Map<String, Object> map);
	/**描述：分页查找不在角色下的所有用户
	 * 作者：Chenxj
	 * 日期：2015年11月14日 - 下午2:46:03
	 * @param map
	 * @return
	 */
	public Map<String, Object> findUsersNotInRoleByPage(Map<String, Object> map);
	/**描述：
	 * 作者：Chenxj
	 * 日期：2015年11月14日 - 下午11:15:09
	 * @param map
	 * @return
	 */
	public boolean addUserToRole(Map<String, Map<String, Object>> map);
	/**描述：
	 * 作者：Chenxj
	 * 日期：2015年11月14日 - 下午11:15:18
	 * @param map
	 * @return
	 */
	public boolean removeUserFromRole(Map<String, Map<String, Object>> map);
	/**
	 * 描述：查找所有角色及是否在白名单中
	 * 作者：Chenxj
	 * 日期：2016年2月14日 - 下午8:05:35
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>>findRolesWithWhiteListASChecked(Object id);
}
