package com.ccloomi.web.system.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authz.AuthorizationFilter;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemAuthorizationFilter
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月25日-下午10:51:54
 */
public class SystemAuthorizationFilter extends AuthorizationFilter{

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)throws Exception {
		System.out.println(response);
		return false;
	}

}
