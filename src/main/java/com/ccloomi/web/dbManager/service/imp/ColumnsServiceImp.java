package com.ccloomi.web.dbManager.service.imp;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.web.dbManager.entity.ColumnsEntity;
import com.ccloomi.web.dbManager.service.ColumnsService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ColumnsServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午4:06:59
 */
@Service("columnsService")
public class ColumnsServiceImp extends GenericService<ColumnsEntity> implements ColumnsService{

	@Override
	public List<ColumnsEntity> findColumnsByTableSchemaAndTableName(String tableSchema,String tableName) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("*")
		.FROM(new ColumnsEntity(), "c")
		.WHERE("c.TABLE_SCHEMA=?", tableSchema)
		.AND("c.TABLE_NAME=?", tableName);
		return findBySQLGod(sm, ColumnsEntity.class);
	}
}
