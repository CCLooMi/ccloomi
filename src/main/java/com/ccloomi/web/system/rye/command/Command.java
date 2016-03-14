package com.ccloomi.web.system.rye.command;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.core.common.entity.IdEntity;
import com.mongodb.client.MongoDatabase;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Command
 * 类 描 述：命令令接口
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:03:05
 */
public interface Command <dao extends BaseDao<? extends IdEntity>>{
	public void doUpdate(dao dao);
	public void doRollback(MongoDatabase mongoDatabase);
}
