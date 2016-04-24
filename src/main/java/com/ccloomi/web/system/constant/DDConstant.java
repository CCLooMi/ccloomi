package com.ccloomi.web.system.constant;

import java.util.Map;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DDConstant
 * 类 描 述：数据字典值获取为常量
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月21日-下午4:23:11
 */
public class DDConstant extends BaseDDConstant{
	public static Map<String,String>shooterMap(){
		return getConstantMap("shooter");
	}
	public static Map<String, String>stackexchangeMap(){
		return getConstantMap("stackexchange");
	}
	public static Map<String, String>thsMap(){
		return getConstantMap("ths");
	}
	public static Map<String, String>gaodeMap(){
		return getConstantMap("lbs.amap.com");
	}
}
