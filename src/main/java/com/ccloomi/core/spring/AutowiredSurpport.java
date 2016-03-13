package com.ccloomi.core.spring;

import java.lang.reflect.Field;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.util.SpringContextUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：AutowiredSurpport
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-上午9:37:54
 */
public abstract class AutowiredSurpport {
	protected Logger log=LoggerFactory.getLogger(getClass());
	public AutowiredSurpport(){
		injectproperty();
	}
	/**
	 * 描述：Spring注入带Autowired注解属性
	 * 作者：Chenxj
	 * 日期：2015年9月22日 - 下午10:56:28
	 * @throws Exception
	 */
	private void injectproperty(){
		Class<?>c=this.getClass();
		Field[]fields=c.getDeclaredFields();
		for(Field field:fields){
			//必须是包含Autowired注解的属性才进行注入处理
			if(field.getDeclaredAnnotation(Autowired.class)!=null){
				//获取属性的名称
				String fieldName=field.getName();
				//根据属性的名称和类型从spring容器中获取实例
				Object value=SpringContextUtil.getBean(fieldName, field.getType());
				//修改属性的访问性为public
				field.setAccessible(true);
				//设置属性值为从spring中取出的实例对象
				try {
					field.set(this, value);
				} catch (Exception e) {
					log.error("Autowired异常::", e);
				}
			}
		}
	}
}
