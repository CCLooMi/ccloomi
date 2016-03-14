package com.ccloomi.web.system.rye.hub;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.hub.BaseHub;
import com.ccloomi.web.system.rye.command.SyncCommand;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：CommandHub
 * 类 描 述：命令收集集中处理线程
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:52:01
 */
public class CommandHub extends BaseHub<SyncCommand<? extends BaseDao<? extends IdEntity>>>{
	@Override
	public void processData(SyncCommand<? extends BaseDao<? extends IdEntity>> obj) {
		try{
			//执行指令中的doUpdate方法
			obj.doUpdate();
		}catch(Exception e){
			//执行指令中的doRollback方法
			obj.doRollback();
			log.error("更新操作出现异常，已回滚", e);
		}
	}
}
