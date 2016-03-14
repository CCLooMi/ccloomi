package com.ccloomi.web.system.rye.command;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.hub.HubFactory;
import com.ccloomi.web.system.rye.hub.CommandHub;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SyncCommand
 * 类 描 述：命令
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:00:49
 */
public class SyncCommand<dao extends BaseDao<? extends IdEntity>>{
	
	private dao dao;
	private Command<dao> command;
	
	/**
	 * 命令构造方法
	 * @param dao 执行数据库操作的dao
	 * @param command 命令接口
	 */
	public SyncCommand(dao dao,Command<dao> command){
		this.dao=dao;
		this.command=command;
	}
	
	public void doUpdate() {
		command.doUpdate(dao);
	}
	public void doRollback() {
		command.doRollback(dao);
	}
	/**
	 * 描述：发送命令到指令集中处理线程
	 * 作者：Chenxj
	 * 日期：2016年3月13日 - 下午11:08:40
	 * @return
	 */
	public SyncCommand<dao>fire(){
		CommandHub hub=HubFactory.getHub(CommandHub.class);
		hub.addData(this);
		return this;
	}
}
