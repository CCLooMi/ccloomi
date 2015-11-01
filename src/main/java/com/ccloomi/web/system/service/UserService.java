package com.ccloomi.web.system.service;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.UserEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午10:33:45
 */
public interface UserService extends BaseService<UserEntity> {
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年10月31日 - 下午9:40:23
	 * @param username
	 * @param password
	 * @return
	 */
	public UserEntity findByUsernameAndPassword(String username,String password);
}
