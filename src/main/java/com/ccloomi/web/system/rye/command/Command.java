package com.ccloomi.web.system.rye.command;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.hub.HubFactory;
import com.ccloomi.core.spring.AutowiredSurpport;
import com.ccloomi.web.system.rye.hub.CommandHub;
import com.mongodb.client.MongoDatabase;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Command
 * 类 描 述：命令
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:00:49
 */
public abstract class Command<T extends IdEntity> extends AutowiredSurpport implements Directive<T>{
	
	@Autowired
	protected MongoDatabase mongoDatabase;
	
	/**
	 * 描述：发送命令到指令集中处理线程
	 * 作者：Chenxj
	 * 日期：2016年3月13日 - 下午11:08:40
	 * @return
	 */
	public Command<T>fire(){
		CommandHub hub=HubFactory.getHub(CommandHub.class);
		hub.addData(this);
		return this;
	}
}
