package com.ccloomi.core.component.sms.yp;

import java.util.Map;

import com.ccloomi.core.component.sms.SmsSender;
import com.ccloomi.core.component.sms.SmsSenderFactory;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：YpSmsSenderFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:50:22
 */
public class YpSmsSenderFactory extends SmsSenderFactory{
	
	protected YpSmsSenderFactory(){
		super();
	}
	public static synchronized SmsSenderFactory getInstance(){
		if(instance==null){
			instance=new YpSmsSenderFactory();
		}
		return instance;
	}

	@Override
	protected SmsSender smsSender() {
		Map<String, String>initP=getInitProperties();
		return new YpSmsSender(initP.get("url_yp"), initP.get("apikey"), initP.get("extend"), initP.get("uid"), initP.get("callback_url"));
	}
}
