package com.ccloomi.web.system.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.ccloomi.core.spring.AutowiredSurpport;
import com.ccloomi.core.util.StringUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ServiceAOP
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-上午9:56:41
 */
@Aspect
public class ServiceAOP extends AutowiredSurpport{

	@Pointcut("execution( * com.ccloomi.core.common.service.AbstractService+.*(..))")
	public void _serviceImp(){};
	@Pointcut("execution(@com.ccloomi.core.annotation.OpenCache * *(..))")
	public void _openCache(){};
	
	@Around("_serviceImp()||_openCache()")
	public Object serviceImp(ProceedingJoinPoint call)throws Throwable {
		MethodSignature ms=(MethodSignature) call.getSignature();
		Object target=call.getTarget();
		log.debug("ServiceAOP拦截到<span class=\"class\">[{}]</span>类方法::<span class=\"method\">[{}(<span class=\"parameter\">{}</span>)]</span>执行",target.getClass(),ms.getMethod().getName(),StringUtil.join(",", call.getArgs()));
		return call.proceed();
	}
}
