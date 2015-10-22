package com.ccloomi.core.tag;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：BaseTag
 * 类 描 述：所有需要在标签中使用spring注入功能的tag只需要继承此基类即可
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月20日-上午8:20:22
 */
public abstract class BaseTag extends RequestContextAwareTag{
	private static final long serialVersionUID = 6603417227782412412L;
	private boolean isInit=false;
	protected JspWriter out;
	@Override
	protected int doStartTagInternal() throws Exception {
		init();
		doTag();
		return EVAL_PAGE;
	}
	/**
	 * 描述：初始化必要属性
	 * 作者：Chenxj
	 * 日期：2015年9月22日 - 下午10:56:10
	 * @throws Exception
	 */
	private void init()throws Exception{
		out=pageContext.getOut();
		if(!isInit){
			injectproperty();
			isInit=true;
		}
	}
	/**
	 * 描述：Spring注入带Autowired注解属性
	 * 作者：Chenxj
	 * 日期：2015年9月22日 - 下午10:56:28
	 * @throws Exception
	 */
	private void injectproperty() throws Exception{
		Class<?>c=this.getClass();
		Field[]fields=c.getDeclaredFields();
		for(Field field:fields){
			//必须是包含Autowired注解的属性才进行注入处理
			if(field.getDeclaredAnnotation(Autowired.class)!=null){
				//获取属性的名称
				String fieldName=field.getName();
				//根据属性的名称和类型从spring容器中获取实例
				Object value=getRequestContext().getWebApplicationContext().getBean(fieldName, field.getType());
				//修改属性的访问性为public
				field.setAccessible(true);
				//设置属性值为从spring中取出的实例对象
				field.set(this, value);
			}
		}
	}
	public abstract void doTag() throws JspException,IOException;
}
