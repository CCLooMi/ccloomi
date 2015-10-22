package com.ccloomi.core.component.sms;

import java.util.Map;

import com.ccloomi.core.component.sms.cl.ClSmsSenderFactory;
import com.ccloomi.core.component.sms.yp.YpSmsSenderFactory;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SmsSenderFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:50:50
 */
public abstract class SmsSenderFactory {
	protected static SmsSenderFactory instance;
	private SmsSender smsSender;
	private Map<String, String>initProperties;
	public static synchronized SmsSenderFactory getInstance(String type){
		SmsSenderEnum ftype=SmsSenderEnum.valueOf(type);
		if(instance==null){
			if(ftype==SmsSenderEnum.CL){
				instance=ClSmsSenderFactory.getInstance();
			}else if(ftype==SmsSenderEnum.YP){
				instance=YpSmsSenderFactory.getInstance();
			}
		}
		return instance;
	}
	public synchronized SmsSender createSmsSender(){
		if(smsSender==null){
			smsSender=instance.smsSender();
		}
		return smsSender;
	}
	protected abstract SmsSender smsSender();
	/**
	 * 获取：initProperties
	 */
	public Map<String, String> getInitProperties() {
		return initProperties;
	}
	/**
	 * 设置：initProperties
	 */
	public void setInitProperties(Map<String, String> initProperties) {
		this.initProperties = initProperties;
	}
}
