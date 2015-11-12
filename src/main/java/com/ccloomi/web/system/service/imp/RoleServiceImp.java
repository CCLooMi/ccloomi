package com.ccloomi.web.system.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.AbstractService;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.system.dao.RoleDao;
import com.ccloomi.web.system.dao.RolePermissionDao;
import com.ccloomi.web.system.dao.RoleUserDao;
import com.ccloomi.web.system.dao.RoleViewDao;
import com.ccloomi.web.system.entity.PermissionEntity;
import com.ccloomi.web.system.entity.RoleEntity;
import com.ccloomi.web.system.entity.RolePermissionEntity;
import com.ccloomi.web.system.entity.RoleUserEntity;
import com.ccloomi.web.system.entity.RoleViewEntity;
import com.ccloomi.web.system.entity.ViewEntity;
import com.ccloomi.web.system.service.RoleService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月4日-上午8:55:45
 */
@Service("roleService")
public class RoleServiceImp extends AbstractService<RoleEntity> implements RoleService{
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private RoleViewDao roleViewDao;
	@Autowired
	private RoleUserDao roleUserDao;
	@Autowired
	private RolePermissionDao rolePermissionDao;
	/**获取 roleDao*/
	public RoleDao getRoleDao() {
		return roleDao;
	}
	/**设置 roleDao*/
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}
	/**获取 roleViewDao*/
	public RoleViewDao getRoleViewDao() {
		return roleViewDao;
	}
	/**设置 roleViewDao*/
	public void setRoleViewDao(RoleViewDao roleViewDao) {
		this.roleViewDao = roleViewDao;
	}
	/**获取 roleUserDao*/
	public RoleUserDao getRoleUserDao() {
		return roleUserDao;
	}
	/**设置 roleUserDao*/
	public void setRoleUserDao(RoleUserDao roleUserDao) {
		this.roleUserDao = roleUserDao;
	}
	/**获取 rolePermissionDao*/
	public RolePermissionDao getRolePermissionDao() {
		return rolePermissionDao;
	}
	/**设置 rolePermissionDao*/
	public void setRolePermissionDao(RolePermissionDao rolePermissionDao) {
		this.rolePermissionDao = rolePermissionDao;
	}
	@Override
	public Map<String, Object> findRolesByPage(Map<String, Object> map) {
		Map<String, Object>rm=new HashMap<>();
		int page=-1+(int) map.get("pageNumber");
		int pageSize=(int) map.get("pageSize");
		String keywords=(String) map.get("keywords");
		SQLMaker sm=new SQLMaker();
		sm.SELECT("*")
		.FROM(new RoleEntity(), "r");
		if(keywords!=null){
			sm.WHERE("r.id LIKE '%?%' OR r.name LIKE '%?%' OR r.code LIKE '%?%'".replaceAll("\\?", keywords));
		}
		sm.LIMIT(page*pageSize, pageSize);
		if(page==0){
			rm.put("totalNumber", countBySQLGod(sm));
		}
		List<Map<String, Object>>ls=findBySQLGod(sm);
		rm.put("data", ls);
		return rm;
	}
	@Override
	public List<ViewEntity> findViewsByIdUser(Object idUser) {
		SQLMaker sm=new SQLMaker();
		sm.SELECT("v.*")
		.FROM(new RoleUserEntity(), "ru")
		.LEFT_JOIN(new RoleViewEntity(), "rv", "ru.idRole=rv.idRole")
		.LEFT_JOIN(new ViewEntity(), "v", "rv.idView=v.id")
		.WHERE("ru.idUser=?", idUser);
		return findBySQLGod(sm, ViewEntity.class);
	}
	@Override
	public List<String> findPermissionsByIdUser(Object idUser) {
		List<String>ls=new ArrayList<>();
		SQLMaker sm=new SQLMaker();
		sm.SELECT("p.code")
		.FROM(new RoleUserEntity(), "ru")
		.LEFT_JOIN(new RolePermissionEntity(), "rp", "ru.idRole=rp.idRole")
		.LEFT_JOIN(new PermissionEntity(), "p", "rp.idPermission=p.id")
		.WHERE("ru.idUser=?", idUser);
		for(Map<String, Object>m:findBySQLGod(sm)){
			ls.add((String)m.get("code"));
		}
		return ls;
	}
	@Override
	public List<String> findRolesByIdUser(Object idUser) {
		List<String>ls=new ArrayList<>();
		SQLMaker sm=new SQLMaker();
		sm.SELECT("r.code")
		.FROM(new RoleUserEntity(), "ru")
		.LEFT_JOIN(new RoleEntity(), "r", "ru.idRole=r.id")
		.WHERE("ru.idUser=?", idUser);
		for(Map<String, Object>m:findBySQLGod(sm)){
			ls.add((String)m.get("code"));
		}
		return ls;
	}
	@Override
	@SuppressWarnings("unchecked")
	public boolean saveViewJstreeData(Map<String, Object> map) {
		List<String>remove=(List<String>) map.get("remove");
		List<String>add=(List<String>) map.get("add");
		Map<String, Object>role=(Map<String, Object>) map.get("role");
		System.out.println("remove::"+remove);
		System.out.println("add::"+add);
		System.out.println("role::"+role);
		return false;
	}
	@Override
	@SuppressWarnings("unchecked")
	public boolean savePermissionJstreeData(Map<String, Object> map) {
		List<String>remove=(List<String>) map.get("remove");
		List<String>add=(List<String>) map.get("add");
		Map<String, Object>role=(Map<String, Object>) map.get("role");
		System.out.println("remove::"+remove);
		System.out.println("add::"+add);
		System.out.println("role::"+role);
		return false;
	}
}
