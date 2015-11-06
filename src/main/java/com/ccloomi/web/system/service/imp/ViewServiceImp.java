package com.ccloomi.web.system.service.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.system.entity.ViewEntity;
import com.ccloomi.web.system.service.ViewService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ViewServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午10:59:25
 */
@Service("viewService")
public class ViewServiceImp extends GenericService<ViewEntity> implements ViewService{

	@Override
	public Map<String, Object> findViewsByPage(Map<String, Object> map) {
		Map<String, Object>rm=new HashMap<>();
		int page=-1+(int) map.get("pageNumber");
		int pageSize=(int) map.get("pageSize");
		String keywords=(String) map.get("keywords");
		SQLMaker sm=new SQLMaker();
		sm.SELECT("*")
		.FROM(new ViewEntity(), "v");
		if(keywords!=null){
			sm.WHERE("v.id LIKE '%?%' OR v.name LIKE '%?%' OR v.url LIKE '%?%' OR v.pid LIKE '%?%' OR v.iconClass LIKE '%?%' OR v.type LIKE '%?%' OR v.idRoot LIKE '%?%'".replaceAll("\\?", keywords));
		}
		sm.LIMIT(page*pageSize, pageSize);
		if(page==0){
			rm.put("totalNumber", countBySQLGod(sm));
		}
		List<Map<String, Object>>ls=findBySQLGod(sm);
		rm.put("data", ls);
		return rm;
	}
	
}
