package com.ccloomi.core.util;

import java.lang.reflect.Method;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ClassUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月16日-下午11:09:50
 */
public class ClassUtil {
	public static Method getMethod(Class<?>clazz,String property,Class<?>...parameterTypes){
		String mName=property.substring(0,1).toUpperCase()+property.substring(1);
		String getMethodName="get"+mName;
		try {
			return clazz.getDeclaredMethod(getMethodName, parameterTypes);
		} catch (Exception e) {
			return null;
		}
	}
	public static Method setMethod(Class<?>clazz,String property,Class<?>...parameterTypes){
		String mName=property.substring(0,1).toUpperCase()+property.substring(1);
		String getMethodName="set"+mName;
		try {
			return clazz.getDeclaredMethod(getMethodName, parameterTypes);
		} catch (Exception e) {
			return null;
		}
	}
}
