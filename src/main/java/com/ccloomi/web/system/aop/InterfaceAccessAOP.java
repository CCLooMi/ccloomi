package com.ccloomi.web.system.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.util.StringUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：InterfaceAccessAOP
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月22日-下午9:11:49
 */
@Aspect
public class InterfaceAccessAOP {
	private final Logger log=LoggerFactory.getLogger(getClass());
	
	@Pointcut("execution( * com.ccloomi.web.*.controller.*.*(..))")
	public void _interfaceAccess(){};

	@Around("_interfaceAccess()")
	public Object interfaceAccess(ProceedingJoinPoint call)throws Throwable {
		MethodSignature ms=(MethodSignature) call.getSignature();
		Object target=call.getTarget();
		log.debug("拦截到<span class=\"class\">[{}]</span>类方法::<span class=\"method\">[{}(<span class=\"parameter\">{}</span>)]</span>执行",target.getClass(),ms.getMethod().getName(),StringUtil.join(",", call.getArgs()));
		return call.proceed();
	}
	
}
