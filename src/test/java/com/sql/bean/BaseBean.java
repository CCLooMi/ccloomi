package com.sql.bean;

import java.io.Serializable;
import java.lang.reflect.Method;

import com.fasterxml.jackson.databind.ObjectMapper;



/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseBean
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午8:42:43
 */
public abstract class BaseBean implements Serializable{
	private static final long serialVersionUID = 8689036927479694060L;
	@SuppressWarnings("unchecked")
	public <T>T getObjectPropertyValue(Object obj,String property){
		String mName=property.substring(0,1).toUpperCase()+property.substring(1);
		String getMethodName="get"+mName;
		try {
			Method m=obj.getClass().getMethod(getMethodName);
			if(m!=null){
				return (T) m.invoke(obj);
			}
		} catch (Exception e) {
		}
		return null;
	}
	@SuppressWarnings("unchecked")
	public <T>T getObjectPropertyValue(Object obj,String property,Class<T>clazz){
		String mName=property.substring(0,1).toUpperCase()+property.substring(1);
		String getMethodName="get"+mName;
		try {
			Method m=obj.getClass().getMethod(getMethodName);
			if(m!=null){
				return (T) m.invoke(obj);
			}
		} catch (Exception e) {
		}
		return null;
	}
	/**
	 * 方法描述：转化为JSON字符串
	 * 作        者：Chenxj
	 * 日        期：2015年6月23日-下午3:28:27
	 */
	@Override
	public String toString(){
		ObjectMapper om=new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (Exception e) {
			return super.toString();
		}finally{
			om=null;
		}
	}
}
