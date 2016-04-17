package com.ccloomi.web.system.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.SpringContextUtil;
import com.ccloomi.web.system.dao.TemplateDao;
import com.ccloomi.web.system.entity.TemplateEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TemplateConstant
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月17日-下午4:57:27
 */
public class TemplateConstant {
	private static TemplateDao templdateDao=SpringContextUtil.getBean(TemplateDao.class);
	private static Map<String, String>map=new HashMap<>();
	private static Map<String,String>templateMap(){
		Map<String, String>mp=new HashMap<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("*")
		.FROM(new TemplateEntity(), "t");
		List<TemplateEntity>templates=templdateDao.findBySQLGod(sm,TemplateEntity.class);
		for(TemplateEntity temp:templates){
			mp.put(temp.getCode(), temp.getContent());
		}
		return mp;
	}
	public synchronized static Map<String, String> getTemplateConstantMap(){
		if(map.isEmpty()){
			map=templateMap();
		}
		return map;
	}
}
