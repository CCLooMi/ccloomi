package com.ccloomi.core.component.sms;

import java.util.HashMap;
import java.util.Map;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SmsCodeUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:50:30
 */
public abstract class SmsCodeUtil {
	protected static Map<String, String>map=new HashMap<String, String>();
	public static String getSmsCodeDesc(String code){
		return map.get(code);
	}
}
