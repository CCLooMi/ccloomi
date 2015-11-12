package com.ccloomi.web.system.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.system.entity.PermissionEntity;
import com.ccloomi.web.system.entity.RolePermissionEntity;
import com.ccloomi.web.system.service.PermissionService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：PermissionServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午11:04:53
 */
@Service("permissionService")
public class PermissionServiceImp extends GenericService<PermissionEntity> implements PermissionService{

	@Override
	public List<Map<String, Object>> findpermissionsTreeByRoleId(Object idRole) {
		SQLMaker sm=new SQLMaker();
		sm.SELECT("p.id")
		.SELECT_AS("p.name", "text")
		.SELECT("p.code")
		.SELECT_AS("IF(rp.idPermission IS NULL,0,1)", "has")
		.FROM(new PermissionEntity(), "p")
		.LEFT_JOIN(new RolePermissionEntity(), "rp", "rp.idPermission=p.id")
		.JOIN_AND("rp.idRole=?", idRole);
		List<Map<String, Object>>ls=findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			long has=(long) m.get("has");
			if(has==1){
				Map<String, Object>state=new HashMap<>();
				state.put("selected", true);
				m.put("state", state);
			}
		}
		return ls;
	}

	@Override
	public List<String> findpermissionIdsByRoleId(Object idRole) {
		List<String>ids=new ArrayList<>();
		SQLMaker sm=new SQLMaker();
		sm.SELECT("rp.idPermission")
		.FROM(new RolePermissionEntity(), "rp")
		.WHERE("rp.idRole=?", idRole);
		List<Map<String, Object>>ls=findBySQLGod(sm);
		for(Map<String, Object>m:ls){
			ids.add(String.valueOf(m.get("idPermission")));
		}
		return ids;
	}
	
}
