package com.ccloomi.core.util;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.ccloomi.core.test.BaseTest;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SpringContextUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月21日-下午4:09:47
 */
public class SpringContextUtil {
	public static <T>T getBean(String name,Class<T>clazz){
		ApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		if(ctx==null){//取测试用的ctx
			ctx=BaseTest.ctx;
		}
		return ctx.getBean(name, clazz);
	}
	public static <T>T getBean(Class<T>clazz){
		ApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		if(ctx==null){//取测试用的ctx
			ctx=BaseTest.ctx;
		}
		return ctx.getBean(clazz);
	}
}
