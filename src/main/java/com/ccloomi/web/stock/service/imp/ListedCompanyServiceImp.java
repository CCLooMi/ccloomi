package com.ccloomi.web.stock.service.imp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.web.stock.dao.ListedCompanyDao;
import com.ccloomi.web.stock.entity.ListedCompanyEntity;
import com.ccloomi.web.stock.service.ListedCompanyService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ListedCompanyServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-下午9:03:30
 */
@Service("listedCompanyService")
public class ListedCompanyServiceImp extends GenericService<ListedCompanyEntity> implements ListedCompanyService{
	@Autowired
	private ListedCompanyDao listedCompanyDao;
	@Override
	public List<Map<String, Object>> getMapExcelTemplateData() {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("lc.name")
		.SELECT_AS("lc.registeredAddress", "address")
		.SELECT_AS("lc.longitude", "x")
		.SELECT_AS("lc.latitude", "y")
		.SELECT_AS("'010-00000000'", "telephone")
		.FROM(new ListedCompanyEntity(), "lc");
		return listedCompanyDao.findBySQLGod(sm);
	}
}
