package com.ccloomi.core.component.catche.redis;

import java.util.Date;

import com.ccloomi.core.component.catche.CacheClient;

import redis.clients.jedis.JedisCluster;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RedisClient
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:54:30
 */
public class RedisClient implements CacheClient{
	private JedisCluster client;
	protected RedisClient(JedisCluster client) {
		this.client=client;
	}
	@Override
	public Object get(String key) {
		return null;
	}

	@Override
	public boolean delete(String key) {
		return false;
	}

	@Override
	public boolean keyExists(String key) {
		return client.exists(key);
	}

	@Override
	public <T> T get(String key, Class<T> claz) {
		return null;
	}

	@Override
	public boolean set(String key, Object value) {
		return false;
	}

	@Override
	public boolean add(String key, Object value) {
		return false;
	}

	@Override
	public boolean replace(String key, Object value) {
		return false;
	}

	@Override
	public boolean set(String key, Object value, Date expiry) {
		return false;
	}

	@Override
	public boolean add(String key, Object value, Date expiry) {
		return false;
	}

	@Override
	public boolean replace(String key, Object value, Date expiry) {
		return false;
	}

}
