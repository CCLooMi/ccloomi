package com.ccloomi.core.component.mongodb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MongoClientFactory
 * 类 描 述：MongoDB工厂类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月11日 - 下午5:29:36
 */
public class MongoClientFactory {
	private static MongoClientFactory instance;
	private MongoClient mongoClient;
	private Map<String, String>initProperties;
	private MongoClientFactory(){}
	public static synchronized MongoClientFactory getInstance(){
		if(instance==null){
			instance=new MongoClientFactory();
		}
		return instance;
	}
	public MongoDatabase getMongoDatabase(){
		return mongoClient.getDatabase(initProperties.get("db"));
	}
	public void init(){
		List<ServerAddress>seeds=new ArrayList<ServerAddress>();
		String[] hosts=initProperties.get("hosts").split(",");
		if(initProperties.get("username")!=null){
			for(String host:hosts){
				String[] hp=host.split(":");
				MongoCredential credential=MongoCredential
						.createCredential(initProperties.get("username"), initProperties.get("db"), initProperties.get("password").toCharArray());
				ServerAddress sa=new ServerAddress(hp[0], Integer.valueOf(hp[1]));
				seeds.add(sa);
				this.mongoClient=new MongoClient(seeds, Arrays.asList(credential));
			}
		}else{
			for(String host:hosts){
				String[] hp=host.split(":");
				ServerAddress sa=new ServerAddress(hp[0], Integer.valueOf(hp[1]));
				seeds.add(sa);
			}
			this.mongoClient=new MongoClient(seeds);
		}
	}
	public void destroy(){
		if(this.mongoClient!=null){
			this.mongoClient.close();
		}
	}
	/**设置：initProperties*/
	public void setInitProperties(Map<String, String> initProperties) {
		this.initProperties = initProperties;
	}
}
