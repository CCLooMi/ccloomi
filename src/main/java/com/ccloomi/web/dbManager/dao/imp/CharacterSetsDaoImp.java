package com.ccloomi.web.dbManager.dao.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.GenericDao;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.dbManager.dao.CharacterSetsDao;
import com.ccloomi.web.dbManager.entity.CharacterSetsEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CharacterSetsImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月9日-下午10:49:24
 */
@Service("characterSetsDaoImp")
public class CharacterSetsDaoImp extends GenericDao<CharacterSetsEntity> implements CharacterSetsDao{

	@Override
	public List<CharacterSetsEntity> findAll() {
		SQLMaker sm=new SQLMaker();
		sm.SELECT("*")
		.FROM(new CharacterSetsEntity(), "cs");
		return findBySQLGod(sm, CharacterSetsEntity.class);
	}
}
