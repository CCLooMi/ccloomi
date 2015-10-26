package com.ccloomi.web.system.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;

import com.ccloomi.core.constant.HttpResponseStatus;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemExceptionHandler
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月26日-下午8:53:24
 */
public class SystemHandlerExceptionResolver extends AbstractHandlerExceptionResolver{

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
		if(ex instanceof UnauthenticatedException){
			response.setStatus(HttpResponseStatus.SC_UNAUTHORIZED);
		}else if(ex instanceof UnauthorizedException){
			response.setStatus(HttpResponseStatus.SC_FORBIDDEN);
		}else{
			logger.error("系统异常::", ex);
		}
		return new ModelAndView();
	}

}
