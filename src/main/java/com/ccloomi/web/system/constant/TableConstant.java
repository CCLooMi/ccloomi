package com.ccloomi.web.system.constant;

import java.util.HashMap;
import java.util.Map;

import com.ccloomi.core.common.dao.BaseDao;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.SpringContextUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TableConstant
 * 类 描 述：表查询常量基类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-上午8:17:11
 */
public abstract class TableConstant {
	private static BaseDao<?> baseDao=SpringContextUtil.getBean("baseDao",BaseDao.class);
	private static Map<String, Map<String, ?>>ddMap=new HashMap<>();
	private static <T>Map<String,T>ddMap(DoSelect doSelect){
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		doSelect.doSelect(sm);
		return doSelect.convertToMap(baseDao.findBySQLGod(sm));
	}
	@SuppressWarnings("unchecked")
	protected static <T>Map<String, T>getConstantMap(String key,DoSelect doSelect){
		if(!ddMap.containsKey(key)){
			ddMap.put(key, ddMap(doSelect));
		}
		return (Map<String, T>) ddMap.get(key);
	}
}
