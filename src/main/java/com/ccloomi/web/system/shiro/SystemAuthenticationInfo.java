package com.ccloomi.web.system.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemAuthenticationInfo
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月27日-下午9:45:35
 */
public class SystemAuthenticationInfo implements AuthenticationInfo{
	private static final long serialVersionUID = -1457527541520212595L;

	/**
	 * 获取主体集合
	 */
	@Override
	public PrincipalCollection getPrincipals() {
		return null;
	}
	/**
	 * 获取证书
	 */
	@Override
	public Object getCredentials() {
		return null;
	}

}
