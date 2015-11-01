package com.ccloomi.core.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.common.bean.Message;

/**
 * 类名：BaseController
 * 描述：基础抽象controller
 * 作者： Chenxj
 * 日期：2015年6月23日 - 下午4:34:11
 */
public abstract class BaseController {
	protected final Logger log=LoggerFactory.getLogger(this.getClass());
	protected Message responseMessageSuccess(){
		Message m=new Message();
		m.setCode("0");
		return m;
	}
	protected Message responseMessageError(String error){
		Message m=new Message();
		m.setCode("1");
		m.setInfo(error);
		return m;
	}
//	/**
//	 * 描述：异常处理方法
//	 * 作者：Chenxj
//	 * 日期：2015年8月13日 - 下午9:28:12
//	 * @param e
//	 */
//	@ExceptionHandler
//	public void exceptionHandler(Exception e){
//		log.error("请求异常", e);
//	}
	
}
