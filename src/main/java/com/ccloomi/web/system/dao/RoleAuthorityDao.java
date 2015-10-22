package com.ccloomi.web.system.dao;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.web.system.entity.RoleAuthorityEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleAuthorityDao
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月5日-下午4:31:52
 */
public interface RoleAuthorityDao extends BaseDao<RoleAuthorityEntity>{
	/**
	 * 描述：获取所有角色权限VisEdges
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午1:55:06
	 * @return
	 */
	public List<Map<String, Object>>getAllRoleAuthorityVisEdges();
}
