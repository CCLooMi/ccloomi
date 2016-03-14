package com.ccloomi.core.test;

import java.lang.reflect.ParameterizedType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：BaseTest
 * 类 描 述：所有要测试Spring的测试类都可以继承此抽象类而获得ctx对象
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午9:33:00
 * @param <T>
 */
public abstract class BaseTest<T>{
	public static final ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/spring.xml");
	private Class<T>entityClass;
	private T testObj;
	public T getTestObj() {
		return testObj;
	}
	public BaseTest(){
		this.entityClass=getEntityClass();
		String className=entityClass.getSimpleName();
		String charAt_0=String.valueOf(className.charAt(0));
		className.replaceFirst(charAt_0,charAt_0.toLowerCase());
		this.testObj=getBean(className.replaceFirst(charAt_0,charAt_0.toLowerCase()));
	}
	
	protected abstract void test(T testObj);
	public void runTest(){
		test(getTestObj());
	}

	protected T getBean(String name){
		return ctx.getBean(name, entityClass);
	}
	/**
	 * 方法描述：获取T的Class
	 * 作者：Chenxj
	 * 日期：2015年7月6日 - 上午11:25:00
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getEntityClass(){
		return (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
