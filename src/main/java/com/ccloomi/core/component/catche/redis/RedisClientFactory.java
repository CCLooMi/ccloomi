package com.ccloomi.core.component.catche.redis;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.ccloomi.core.component.catche.CacheClient;
import com.ccloomi.core.component.catche.CatcheClientFactory;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RedisClientFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:54:37
 */
public class RedisClientFactory extends CatcheClientFactory{

	protected RedisClientFactory(){
		super();
	}
	public static synchronized CatcheClientFactory getInstance(){
		if(instance==null){
			instance=new RedisClientFactory();
		}
		return instance;
	}
	@Override
	public CacheClient cacheClient() {
		Map<String, String>map=getInitProperties();
		String[] servers=map.get("servers").split(",");
		Set<HostAndPort>jedisClusterNodes=new HashSet<HostAndPort>();
		for(String server:servers){
			String s[]=server.split(":");
			jedisClusterNodes.add(new HostAndPort(s[0], Integer.valueOf(s[1])));
		}
		JedisCluster jc=new JedisCluster(jedisClusterNodes);
		return new RedisClient(jc);
	}

}
