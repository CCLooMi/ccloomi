package com.ccloomi.web.dbManager.dao;

import java.util.List;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.web.dbManager.entity.CollationsEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CollationsDao
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月9日-下午10:53:12
 */
public interface CollationsDao extends BaseDao<CollationsEntity>{
	public List<CollationsEntity>findByCharacterName(String characterName);
}
