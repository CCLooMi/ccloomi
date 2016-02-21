package com.ccloomi.web.system.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.SpringContextUtil;
import com.ccloomi.web.system.dao.DataDictionaryDao;
import com.ccloomi.web.system.entity.DataDictionaryEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DDConstant
 * 类 描 述：数据字典常量
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月21日-下午4:16:38
 */
public abstract class BaseDDConstant {
	private static DataDictionaryDao dataDictionaryDao=SpringContextUtil.getBean("dataDictionaryDao", DataDictionaryDao.class);
	private static Map<String, Map<String, String>>ddMap=new HashMap<>();
	protected static Map<String,String>ddMap(String code){
		Map<String, String>map=new HashMap<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("d.id")
		.SELECT("d.name")
		.SELECT("d.vl")
		.FROM(new DataDictionaryEntity(), "d")
		.LEFT_JOIN(new DataDictionaryEntity(), "dd", "d.pid=dd.id")
		.WHERE("dd.code=?",code);
		List<Map<String, Object>>ls=dataDictionaryDao.findBySQLGod(sm);
		if(ls.size()>0){
			for(Map<String, Object>m:ls){
				map.put((String)m.get("name"), (String)m.get("vl"));
			}
		}
		return map;
	}
	protected static Map<String, String>getConstantMap(String code){
		if(!ddMap.containsKey(code)){
			ddMap.put(code, ddMap(code));
		}
		return ddMap.get(code);
	}
}
