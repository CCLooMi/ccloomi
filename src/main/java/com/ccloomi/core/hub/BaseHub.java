package com.ccloomi.core.hub;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseHub
 * 类 描 述：集线器基类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:21:17
 */
public abstract class BaseHub<T> extends Thread{
	protected Logger log=LoggerFactory.getLogger(getClass());
	private Stack<T>stack=new Stack<>();
	public BaseHub(){
		start();
	}
	public void addData(T data){
		stack.add(data);
	}
	public void addAll(Collection<? extends T>data){
		stack.addAll(data);
	}
	public List<T> getData(){
		List<T>ds=new ArrayList<>(stack);
		return ds;
	}
	@SuppressWarnings("unchecked")
	public void run(){
		if(stack.isEmpty()){
			try{
				sleep(500);
				run();
			}catch(InterruptedException e){
				log.error("线程中断,重建线程", e);
				HubFactory.resetHub(getClass(), getTClass());
			}catch(Exception e){
				log.error("线程异常", e);
				run();
			}
		}else{
			while(!stack.empty()){
				processData(stack.pop());
			}
			run();
		}
	}
	public abstract void processData(T obj);

	/**
	 * 方法描述：获取T的Class
	 * 作者：Chenxj
	 * 日期：2015年7月6日 - 上午11:25:00
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getTClass(){
		return (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
}
