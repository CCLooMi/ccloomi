package com.ccloomi.web.system.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.AbstractService;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.system.dao.RoleUserDao;
import com.ccloomi.web.system.dao.UserDao;
import com.ccloomi.web.system.entity.UserEntity;
import com.ccloomi.web.system.service.UserService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午10:34:23
 */
@Service("userService")
public class UserServiceImp extends AbstractService<UserEntity> implements UserService{
	@Autowired
	private UserDao userDao;
	@Autowired
	private RoleUserDao roleUserDao;
	/**获取 userDao*/
	public UserDao getUserDao() {
		return userDao;
	}

	/**设置 userDao*/
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	/**获取 roleUserDao*/
	public RoleUserDao getRoleUserDao() {
		return roleUserDao;
	}

	/**设置 roleUserDao*/
	public void setRoleUserDao(RoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}

	@Override
	public UserEntity findByUsernameAndPassword(String username, String password) {
		SQLMaker sm=new SQLMaker();
		sm.SELECT("*")
		.FROM(new UserEntity(), "u")
		.WHERE("u.username=?", username)
		.AND("u.password=?", password);
		List<UserEntity>ls=userDao.findBySQLGod(sm, UserEntity.class);
		if(ls.size()>0){
			return ls.get(0);
		}
		return null;
	}
}
