package com.ccloomi.web.dbManager.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.web.dbManager.entity.TablesEntity;
import com.ccloomi.web.dbManager.service.TablesService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：TablesServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午4:08:50
 */
@Service("tablesService")
public class TablesServiceImp extends GenericService<TablesEntity> implements TablesService{

	@Override
	public List<TablesEntity> findTablesByTableSchema(String tableSchema) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("*")
		.FROM(new TablesEntity(), "t")
		.WHERE("t.TABLE_SCHEMA=?", tableSchema);
		return findBySQLGod(sm, TablesEntity.class);
	}
}
