package com.ccloomi.web.system.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.ByPageSelect;
import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.web.system.entity.IconEntity;
import com.ccloomi.web.system.service.IconService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：IconServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月14日-下午8:19:50
 */
@Service("iconService")
public class IconServiceImp extends GenericService<IconEntity> implements IconService{
//	@Autowired
//	private IconDao iconDao;
	@Override
	public Map<String, Object> findIconsByPage(Map<String, Object> map) {
		return byPage(map, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				String keywords=(String) map.get("keywords");
				String orderBy=(String) map.get("orderBy");
				sm.SELECT("*")
				.FROM(new IconEntity(), "ic");
				if(keywords!=null){
					sm.WHERE("ic.id LIKE '%?%' OR ic.groupName LIKE '%?%' OR  ic.className LIKE '%?%'".replaceAll("\\?", keywords));
				}
				if(orderBy!=null){
					sm.ORDER_BY("ic."+orderBy);
				}
			}
		});
	}
}
