package com.ccloomi.web.system.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.AbstractService;
import com.ccloomi.core.common.service.ByPageSelect;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
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
	
	@Override
	public UserEntity findByUsernameAndPassword(String username, String password) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
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

	@Override
	public Map<String, Object> findUsersByPage(Map<String, Object> map) {
		return byPage(map, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				String keywords=(String) map.get("keywords");
				sm.SELECT("*")
				.FROM(new UserEntity(), "u");
				if(keywords!=null){
					sm.WHERE("u.username LIKE '%?%' OR u.nickname LIKE '%?%' OR u.id LIKE '%?%'".replaceAll("\\?", keywords));
				}
			}
		});
	}
}
