package com.ccloomi.web.system.dao.imp;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.GenericDao;
import com.ccloomi.web.system.dao.UserDao;
import com.ccloomi.web.system.entity.UserEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserDaoImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午10:26:54
 */
@Service("userDao")
public class UserDaoImp extends GenericDao<UserEntity> implements UserDao{
	@Override
	public List<Map<String, Object>> getAllUserVisNodes() {
		String sql="SELECT id,nickname AS 'label','user' AS 'group',username FROM sys_user";
		return jdbcTemplate.queryForList(sql);
	}
	
}
