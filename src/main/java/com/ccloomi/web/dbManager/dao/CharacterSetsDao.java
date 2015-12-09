package com.ccloomi.web.dbManager.dao;

import java.util.List;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.web.dbManager.entity.CharacterSetsEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CharacterSetsDao
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月9日-下午10:48:46
 */
public interface CharacterSetsDao extends BaseDao<CharacterSetsEntity>{
	public List<CharacterSetsEntity>findAll();
}
