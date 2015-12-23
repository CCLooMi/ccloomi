package com.ccloomi.web.dbManager.dao.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.GenericDao;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.web.dbManager.dao.CollationsDao;
import com.ccloomi.web.dbManager.entity.CollationsEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CollationsDaoImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月9日-下午10:53:35
 */
@Service("collationsDao")
public class CollationsDaoImp extends GenericDao<CollationsEntity> implements CollationsDao{

	@Override
	public List<CollationsEntity> findByCharacterName(String characterName) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("*")
		.FROM(new CollationsEntity(), "c")
		.WHERE("c.character_set_name=?", characterName);
		return findBySQLGod(sm, CollationsEntity.class);
	}
}
