package com.ccloomi.web.system.hub;

import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.directive.Directive;
import com.ccloomi.core.hub.BaseHub;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DirectiveHub
 * 类 描 述：指令收集集中处理线程
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:52:01
 */
public class DirectiveHub extends BaseHub<Directive<? extends IdEntity>>{
	@Override
	public void processData(Directive<? extends IdEntity> obj) {
		try{
			obj.doUpdate();
		}catch(Exception e){
			obj.doRollback();
			log.error("更新操作出现异常，已回滚", e);
		}
	}
}
