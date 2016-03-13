package com.ccloomi.core.hub;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：HubFactory
 * 类 描 述：集线器工厂类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午3:05:09
 */
public class HubFactory {
	protected static final Logger log=LoggerFactory.getLogger(HubFactory.class);
	private static Map<Object, Object>hubMap=new HashMap<>();
	public synchronized static <T extends BaseHub<? extends Object>>T getHub(Class<T>clazz){
		@SuppressWarnings("unchecked")
		T hub=(T) hubMap.get(clazz);
		if(hub==null){
			try{
				hub=clazz.newInstance();
				hubMap.put(clazz, hub);
			}catch(Exception e){
				log.error("获取"+clazz.getName()+"实例异常", e);
			}
		}
		return hub;
	}
	protected static <T extends BaseHub<E>,E> void resetHub(Class<T>clazz,Class<E>claz){
		@SuppressWarnings("unchecked")
		T hub=(T) hubMap.get(clazz);
		if(hub!=null){
			try{
				T hub_new=clazz.newInstance();
				hubMap.put(clazz, hub_new);
				hub_new.addAll(hub.getData());
			}catch(Exception e){
				log.error("重置"+clazz.getName()+"实例异常", e);
			}
		}
	}
}
