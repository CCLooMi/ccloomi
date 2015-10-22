package com.ccloomi.core.component.catche;

import com.ccloomi.core.common.factory.BaseFactory;
import com.ccloomi.core.component.catche.memcached.MemcachedClientFactory;
import com.ccloomi.core.component.catche.redis.RedisClientFactory;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CatcheClientFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:54:12
 */
public abstract class CatcheClientFactory extends BaseFactory{
	protected static CatcheClientFactory instance;
	private CacheClient cacheClient;
	public static synchronized CatcheClientFactory getInstance(String type){
		CacheClientEnum cType=CacheClientEnum.valueOf(type);
		if(instance==null){
			if(cType==CacheClientEnum.MEMCACHED){
				instance=MemcachedClientFactory.getInstance();
			}else if(cType==CacheClientEnum.REDIS){
				instance=RedisClientFactory.getInstance();
			}
		}
		return instance;
	}
	public synchronized CacheClient createCatcheClient(){
		if(cacheClient==null){
			cacheClient=instance.cacheClient();
		}
		return cacheClient;
	}
	public abstract CacheClient cacheClient();
}
