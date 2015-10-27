package com.ccloomi.web.system.shiro;

import java.util.Collection;

import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemAuthorizationInfo
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月27日-下午9:43:33
 */
public class SystemAuthorizationInfo implements AuthorizationInfo{
	private static final long serialVersionUID = -4208747463086302101L;
	
	@Override
	public Collection<String> getRoles() {
		return null;
	}

	@Override
	public Collection<String> getStringPermissions() {
		return null;
	}

	@Override
	public Collection<Permission> getObjectPermissions() {
		return null;
	}

}
