package com.ccloomi.web.system.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.AbstractService;
import com.ccloomi.web.system.dao.AuthorityDao;
import com.ccloomi.web.system.entity.AuthorityEntity;
import com.ccloomi.web.system.service.AuthorityService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AuthorityServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年8月1日-上午9:26:48
 */
@Service("authorityService")
public class AuthorityServiceImp extends AbstractService<AuthorityEntity> implements AuthorityService{
	@Autowired
	private AuthorityDao authorityDao;
	
	/**获取 authorityDao*/
	public AuthorityDao getAuthorityDao() {
		return authorityDao;
	}
	/**设置 authorityDao*/
	public void setAuthorityDao(AuthorityDao authorityDao) {
		this.authorityDao = authorityDao;
	}
}
