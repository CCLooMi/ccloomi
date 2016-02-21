package com.ccloomi.core.util;

import org.springframework.web.context.ContextLoader;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SpringContextUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月21日-下午4:09:47
 */
public class SpringContextUtil {
	public static <T>T getBean(String name,Class<T>clazz){
		return ContextLoader.getCurrentWebApplicationContext().getBean(name, clazz);
	}
}
