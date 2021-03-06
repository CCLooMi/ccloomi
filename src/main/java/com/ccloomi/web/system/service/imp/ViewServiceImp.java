package com.ccloomi.web.system.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.ByPageSelect;
import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.web.system.dao.ViewDao;
import com.ccloomi.web.system.entity.RoleViewEntity;
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
	@Autowired
	private ViewDao viewDao;
	@Override
	public Object save(ViewEntity entity){
		return viewDao.save(entity);
	}
	@Override
	public Map<String, Object> findViewsByPage(Map<String, Object> map) {
		return byPage(map, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				String keywords=(String) map.get("keywords");
				sm.SELECT("*")
				.FROM(new ViewEntity(), "v");
				if(keywords!=null){
					sm.WHERE("v.id LIKE '%?%' OR v.name LIKE '%?%' OR v.url LIKE '%?%' OR v.pid LIKE '%?%' OR v.iconClass LIKE '%?%' OR v.type LIKE '%?%' OR v.idRoot LIKE '%?%'".replaceAll("\\?", keywords));
				}
				sm.ORDER_BY("v.idRoot,v.deepIndex");
			}
		});
	}
	@Override
	public List<Map<String, Object>> findViewsTreeByRoleId(Object idRole) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("v.id")
		.SELECT_AS("v.name", "text")
		.SELECT_AS("v.iconClass", "icon")
		.SELECT_AS("IF(rv.idView IS NULL,0,1)", "has")
		.FROM(new ViewEntity(), "v")
		.LEFT_JOIN(new RoleViewEntity(), "rv", "(rv.idView=v.id AND rv.idRole=?)",idRole)
		.WHERE("v.deepIndex=0");
		List<Map<String, Object>>ls=viewDao.findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			//根不能选中，不然下面所有的子选项都会选中
			List<Map<String, Object>>ls2=findViewsTreeByPid(idRole, m.get("id"));
			if(ls2.size()>0){
				m.put("children", ls2);
			}else{//只有没有子选项才设置state
				long has=(long) m.get("has");
				if(has==1){
					Map<String, Object>state=new HashMap<>();
					state.put("selected", true);
					m.put("state", state);
				}
			}
		}
		return ls;
	}
	private List<Map<String, Object>> findViewsTreeByPid(Object idRole,Object pid){
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("v.id")
		.SELECT_AS("v.name", "text")
		.SELECT_AS("v.iconClass", "icon")
		.SELECT_AS("IF(rv.idView IS NULL,0,1)", "has")
		.FROM(new ViewEntity(), "v")
		.LEFT_JOIN(new RoleViewEntity(), "rv", "(rv.idView=v.id AND rv.idRole=?)",idRole)
		.WHERE("v.pid=?", pid);
		List<Map<String, Object>>ls=viewDao.findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			List<Map<String, Object>>ls2=findViewsTreeByPid(idRole, m.get("id"));
			if(ls2.size()>0){
				m.put("children", ls2);
			}else{//只有没有子选项才设置state
				long has=(long) m.get("has");
				if(has==1){
					Map<String, Object>state=new HashMap<>();
					state.put("selected", true);
					m.put("state", state);
				}
			}
		}
		return ls;
	}
	private List<Map<String, Object>> findViewsTreeByPid(Object pid){
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("v.id")
		.SELECT_AS("v.name", "text")
		.SELECT_AS("v.iconClass", "icon")
		.FROM(new ViewEntity(), "v")
		.WHERE("v.pid=?", pid);
		List<Map<String, Object>>ls=viewDao.findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			List<Map<String, Object>>ls2=findViewsTreeByPid(m.get("id"));
			if(ls2.size()>0){
				m.put("children", ls2);
			}
		}
		return ls;
	}
	@Override
	public List<String> findViewIdsByRoleId(Object idRole){
		List<String>ids=new ArrayList<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("rv.idView")
		.FROM(new RoleViewEntity(), "rv")
		.WHERE("rv.idRole=?", idRole);
		List<Map<String, Object>>ls=viewDao.findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			ids.add(String.valueOf(m.get("idView")));
		}
		return ids;
	}
	@Override
	public Map<String, Object> findVisViews() {
		Map<String, Object>map=new HashMap<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("v.*")
		.SELECT_AS("v.name", "label")
		.SELECT_AS("v.idRoot", "group")
		.FROM(new ViewEntity(), "v");
		List<Map<String, Object>>nodes=viewDao.findBySQLGod(sm);
		sm.clean();
		sm.SELECT_AS("v.id", "from")
		.SELECT_AS("v.pid", "to")
		.FROM(new ViewEntity(), "v")
		.WHERE("v.pid IS NOT NULL")
		.AND("v.pid !=''");
		List<Map<String, Object>>edges=viewDao.findBySQLGod(sm);
		map.put("nodes", nodes);
		map.put("edges", edges);
		return map;
	}
	@Override
	public List<Map<String, Object>> findViewsTree() {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("v.id")
		.SELECT_AS("v.name", "text")
		.SELECT_AS("v.iconClass", "icon")
		.FROM(new ViewEntity(), "v")
		.WHERE("v.deepIndex=0");
		List<Map<String, Object>>ls=viewDao.findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			//根不能选中，不然下面所有的子选项都会选中
			List<Map<String, Object>>ls2=findViewsTreeByPid(m.get("id"));
			if(ls2.size()>0){
				m.put("children", ls2);
			}
		}
		return ls;
	}
}
