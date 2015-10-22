package com.ccloomi.web.system.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.web.system.entity.RoleUserEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleUser
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午10:38:13
 */
public interface RoleUserDao extends BaseDao<RoleUserEntity>{
	/**
	 * 描述：通过用户ID查找角色ID列表
	 * 作者：Chenxj
	 * 日期：2015年7月3日 - 下午10:45:24
	 * @param idUser
	 * @return
	 */
	public List<Object> getIdRoleByIdUser(Serializable idUser);
	/**
	 * 描述：通过角色ID查找用户ID列表
	 * 作者：Chenxj
	 * 日期：2015年7月3日 - 下午10:45:28
	 * @param idRole
	 * @return
	 */
	public List<Object> getIdUserByIdRole(Serializable idRole);
	/**
	 * 描述：获取所有角色用户VisEdges
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午1:47:47
	 * @return
	 */
	public List<Map<String, Object>>getAllRoleUserVisEdges();
}
