package com.ccloomi.web.system.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.ccloomi.web.system.shiro.SystemAuthenticationInfo;
import com.ccloomi.web.system.shiro.SystemAuthorizationInfo;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemRealm
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月27日-下午9:27:55
 */
public class SystemRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SystemAuthorizationInfo info=new SystemAuthorizationInfo();
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		SystemAuthenticationInfo info=new SystemAuthenticationInfo();
		return info;
	}

}
