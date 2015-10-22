package com.ccloomi.web.system.dao;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.web.system.entity.RoleEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleDao
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午11:16:04
 */
public interface RoleDao extends BaseDao<RoleEntity>{
	/**
	 * 描述：获取所有角色VisNodes
	 * 作者：Chenxj
	 * 日期：2015年8月1日 - 下午1:27:04
	 * @return
	 */
	public List<Map<String,Object>>getAllRoleVisNodes();
}
