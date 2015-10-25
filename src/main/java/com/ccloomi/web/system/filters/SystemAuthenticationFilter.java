package com.ccloomi.web.system.filters;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.AuthenticationFilter;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemAuthenticationFilter
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月25日-下午10:06:11
 */
public class SystemAuthenticationFilter extends AuthenticationFilter{

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		System.out.println(response);
		return false;
	}

}
