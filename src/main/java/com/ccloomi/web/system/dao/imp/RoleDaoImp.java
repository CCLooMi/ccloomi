package com.ccloomi.web.system.dao.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.GenericDao;
import com.ccloomi.web.system.dao.RoleDao;
import com.ccloomi.web.system.entity.RoleEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleDaoImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午11:16:35
 */
@Service("roleDao")
public class RoleDaoImp extends GenericDao<RoleEntity> implements RoleDao{
	@Override
	protected Class<RoleEntity> getEntityClass() {
		return RoleEntity.class;
	}

	@Override
	public List<Map<String, Object>> getAllRoleVisNodes() {
		String sql="SELECT id,code,name AS 'label','role' AS 'group' FROM sys_role";
		return jdbcTemplate.queryForList(sql);
	}
	
}
