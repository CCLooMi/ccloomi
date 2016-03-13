package com.ccloomi.core.directive;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.hub.HubFactory;
import com.ccloomi.core.spring.AutowiredSurpport;
import com.ccloomi.web.system.hub.DirectiveHub;
import com.mongodb.client.MongoDatabase;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseDirective
 * 类 描 述：指令基类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:00:49
 */
public abstract class BaseDirective<T extends IdEntity> extends AutowiredSurpport implements Directive<T>{
	@Autowired
	protected MongoDatabase mongoDatabase;
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2016年3月13日 - 下午11:08:40
	 * @return
	 */
	protected BaseDirective<T>fire(){
		DirectiveHub hub=HubFactory.getHub(DirectiveHub.class);
		hub.addData(this);
		return this;
	}
}
