package com.ccloomi.web.system.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.SpringContextUtil;
import com.ccloomi.web.stock.dao.ListedCompanyDao;
import com.ccloomi.web.stock.entity.ListedCompanyEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ListedCompanyNameConstant
 * 类 描 述：上市公司名称
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月23日-下午10:55:18
 */
public class ListedCompanyNameConstant {
	private static ListedCompanyDao listedCompanyDao=SpringContextUtil.getBean(ListedCompanyDao.class);
	private static Map<String, String>map=new HashMap<>();
	/**
	 * 描述：map{name->id,id->name}
	 * 作者：Chenxj
	 * 日期：2016年4月23日 - 下午10:59:56
	 * @return
	 */
	private static Map<String,String>listedCompanyNameMap(){
		Map<String, String>mp=new HashMap<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("lc.id")
		.SELECT("lc.name")
		.FROM(new ListedCompanyEntity(), "lc");
		List<ListedCompanyEntity>listedCompanyName=listedCompanyDao.findBySQLGod(sm,ListedCompanyEntity.class);
		for(ListedCompanyEntity lc:listedCompanyName){
			mp.put(lc.getName(),lc.getId());
			mp.put(lc.getId(), lc.getName());
		}
		return mp;
	}
	/**
	 * 描述：map{name->id,id->name}
	 * 作者：Chenxj
	 * 日期：2016年4月23日 - 下午11:00:28
	 * @return
	 */
	public synchronized static Map<String, String> getListedCompanyNameConstantMap(){
		if(map.isEmpty()){
			map=listedCompanyNameMap();
		}
		return map;
	}
}
