package com.ccloomi.web.system.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.AbstractService;
import com.ccloomi.core.common.service.ByPageSelect;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.projManager.entity.WhiteListEntity;
import com.ccloomi.web.system.dao.RoleDao;
import com.ccloomi.web.system.dao.RolePermissionDao;
import com.ccloomi.web.system.dao.RoleUserDao;
import com.ccloomi.web.system.dao.RoleViewDao;
import com.ccloomi.web.system.entity.PermissionEntity;
import com.ccloomi.web.system.entity.RoleEntity;
import com.ccloomi.web.system.entity.RolePermissionEntity;
import com.ccloomi.web.system.entity.RoleUserEntity;
import com.ccloomi.web.system.entity.RoleViewEntity;
import com.ccloomi.web.system.entity.UserEntity;
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
	
	@Override
	public Map<String, Object> findRolesByPage(Map<String, Object> map) {
		return byPage(map, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				String keywords=(String) map.get("keywords");
				sm.SELECT("*")
				.FROM(new RoleEntity(), "r");
				if(keywords!=null){
					sm.WHERE("r.id LIKE '%?%' OR r.name LIKE '%?%' OR r.code LIKE '%?%'".replaceAll("\\?", keywords));
				}
			}
		});
	}
	@Override
	public List<ViewEntity> findViewsByIdUser(Object idUser) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("v.*")
		.FROM(new RoleUserEntity(), "ru")
		.LEFT_JOIN(new RoleViewEntity(), "rv", "ru.idRole=rv.idRole")
		.LEFT_JOIN(new ViewEntity(), "v", "rv.idView=v.id")
		.WHERE("ru.idUser=?", idUser);
		return findBySQLGod(sm, ViewEntity.class);
	}
	@Override
	public List<PermissionEntity> findPermissionsByIdUser(Object idUser) {
		List<PermissionEntity>ls=new ArrayList<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("p.*")
		.FROM(new RoleUserEntity(), "ru")
		.LEFT_JOIN(new RolePermissionEntity(), "rp", "ru.idRole=rp.idRole")
		.LEFT_JOIN(new PermissionEntity(), "p", "rp.idPermission=p.id")
		.WHERE("ru.idUser=?", idUser);
		for(PermissionEntity p:findBySQLGod(sm,PermissionEntity.class)){
			ls.add(p);
		}
		return ls;
	}
	@Override
	public List<RoleEntity> findRolesByIdUser(Object idUser) {
		List<RoleEntity>ls=new ArrayList<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("r.*")
		.FROM(new RoleUserEntity(), "ru")
		.LEFT_JOIN(new RoleEntity(), "r", "ru.idRole=r.id")
		.WHERE("ru.idUser=?", idUser);
		for(RoleEntity r:findBySQLGod(sm,RoleEntity.class)){
			ls.add(r);
		}
		return ls;
	}
	@Override
	public List<RoleEntity> findRolesByIdTarget(Object id) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("r.*")
		.FROM(new WhiteListEntity(), "wl")
		.LEFT_JOIN(new RoleEntity(), "r", "wl.idRole=r.id")
		.WHERE("wl.idTarget=?", id);
		return roleDao.findBySQLGod(sm, RoleEntity.class);
	}
	@Override
	@SuppressWarnings("unchecked")
	public boolean saveViewJstreeData(Map<String, Object> map) {
		List<String>remove=(List<String>) map.get("remove");
		List<String>add=(List<String>) map.get("add");
		Map<String, Object>role=(Map<String, Object>) map.get("role");
		int rSize=remove.size();
		int aSize=add.size();
		if(rSize>aSize){//先更新再删除
			List<Object[]>batchArgs=new ArrayList<>();
			int r=0;
			if(aSize>0){
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.UPDATE(new RoleViewEntity(), "rv")
				.SET("rv.idView=?")
				.WHERE("rv.idView=?")
				.AND("rv.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				for(int i=0;i<aSize;i++){
					Object[]arg=new Object[2];
					arg[0]=add.get(i);
					arg[1]=remove.remove(i);
					batchArgs.add(arg);
				}
				sm.setBatchArgs(batchArgs);
				r=batchUpdateBySQLGod(sm).length;
				rSize=remove.size();
			}
			if(rSize>0){
				batchArgs=new ArrayList<>();
				for(int i=0;i<rSize;i++){
					Object[]arg=new Object[1];
					arg[0]=remove.get(i);
					batchArgs.add(arg);
				}
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.DELETE(new RoleViewEntity(), "rv")
				.WHERE("rv.idView=?")
				.AND("rv.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				sm.setBatchArgs(batchArgs);
				r+=batchUpdateBySQLGod(sm).length;
			}
			return r>0?true:false;
		}else if(rSize<aSize){//先更新再添加
			List<Object[]>batchArgs=new ArrayList<>();
			int r=0;
			if(rSize>0){
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.UPDATE(new RoleViewEntity(), "rv")
				.SET("rv.idView=?")
				.WHERE("rv.idView=?")
				.AND("rv.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				for(int i=0;i<rSize;i++){
					Object[]arg=new Object[2];
					arg[0]=add.remove(i);
					arg[1]=remove.get(i);
					batchArgs.add(arg);
				}
				sm.setBatchArgs(batchArgs);
				r=batchUpdateBySQLGod(sm).length;
				
				aSize=add.size();
			}
			if(aSize>0){
				List<RoleViewEntity>entities=new ArrayList<>();
				for(int i=0;i<aSize;i++){
					RoleViewEntity entity=new RoleViewEntity();
					entity.setId(StringUtil.buildUUID());
					entity.setIdRole(String.valueOf(role.get("id")));
					entity.setIdView(add.get(i));
					entities.add(entity);
				}
				r+=roleViewDao.batchSave(entities).length;
			}
			return r>0?true:false;
		}else if(rSize==aSize){//只需要更新
			int r=0;
			if(rSize>0){
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.UPDATE(new RoleViewEntity(), "rv")
				.SET("rv.idView=?")
				.WHERE("rv.idView=?")
				.AND("rv.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				List<Object[]>batchArgs=new ArrayList<>();
				for(int i=0;i<rSize;i++){
					Object[]arg=new Object[2];
					arg[0]=add.get(i);
					arg[1]=remove.get(i);
					batchArgs.add(arg);
				}
				sm.setBatchArgs(batchArgs);
				r=batchUpdateBySQLGod(sm).length;
			}
			return r>0?true:false;
		}
		return false;
	}
	@Override
	@SuppressWarnings("unchecked")
	public boolean savePermissionJstreeData(Map<String, Object> map) {
		List<String>remove=(List<String>) map.get("remove");
		List<String>add=(List<String>) map.get("add");
		Map<String, Object>role=(Map<String, Object>) map.get("role");
		int rSize=remove.size();
		int aSize=add.size();
		if(rSize>aSize){//先更新再删除
			List<Object[]>batchArgs=new ArrayList<>();
			int r=0;
			if(aSize>0){
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.UPDATE(new RolePermissionEntity(), "rp")
				.SET("rp.idPermission=?")
				.WHERE("rp.idPermission=?")
				.AND("rp.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				for(int i=0;i<aSize;i++){
					Object[]arg=new Object[2];
					arg[0]=add.get(i);
					arg[1]=remove.remove(i);
					batchArgs.add(arg);
				}
				sm.setBatchArgs(batchArgs);
				r=batchUpdateBySQLGod(sm).length;
				rSize=remove.size();
			}
			if(rSize>0){
				batchArgs=new ArrayList<>();
				for(int i=0;i<rSize;i++){
					Object[]arg=new Object[1];
					arg[0]=remove.get(i);
					batchArgs.add(arg);
				}
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.DELETE(new RolePermissionEntity(), "rp")
				.WHERE("rp.idPermission=?")
				.AND("rp.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				sm.setBatchArgs(batchArgs);
				r+=batchUpdateBySQLGod(sm).length;
			}
			return r>0?true:false;
		}else if(rSize<aSize){//先更新再添加
			List<Object[]>batchArgs=new ArrayList<>();
			int r=0;
			if(rSize>0){
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.UPDATE(new RolePermissionEntity(), "rp")
				.SET("rp.idPermission=?")
				.WHERE("rp.idPermission=?")
				.AND("rp.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				for(int i=0;i<rSize;i++){
					Object[]arg=new Object[2];
					arg[0]=add.remove(i);
					arg[1]=remove.get(i);
					batchArgs.add(arg);
				}
				sm.setBatchArgs(batchArgs);
				r=batchUpdateBySQLGod(sm).length;
				
				aSize=add.size();
			}
			if(aSize>0){
				List<RolePermissionEntity>entities=new ArrayList<>();
				for(int i=0;i<aSize;i++){
					RolePermissionEntity entity=new RolePermissionEntity();
					entity.setId(StringUtil.buildUUID());
					entity.setIdRole(String.valueOf(role.get("id")));
					entity.setIdPermission(add.get(i));
					entities.add(entity);
				}
				r+=rolePermissionDao.batchSave(entities).length;
			}
			return r>0?true:false;
		}else if(rSize==aSize){//只需要更新
			int r=0;
			if(rSize>0){
				SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
				sm.UPDATE(new RolePermissionEntity(), "rp")
				.SET("rp.idPermission=?")
				.WHERE("rp.idPermission=?")
				.AND("rp.idRole='?'".replaceAll("\\?", String.valueOf(role.get("id"))));
				List<Object[]>batchArgs=new ArrayList<>();
				for(int i=0;i<rSize;i++){
					Object[]arg=new Object[2];
					arg[0]=add.get(i);
					arg[1]=remove.get(i);
					batchArgs.add(arg);
				}
				sm.setBatchArgs(batchArgs);
				r=batchUpdateBySQLGod(sm).length;
			}
			return r>0?true:false;
		}
		return false;
	}
	@Override
	public Map<String, Object> findUsersInRoleByPage(Map<String, Object> map) {
		return byPage(map, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				String keywords=(String) map.get("keywords");
				@SuppressWarnings("unchecked")
				Map<String, Object>role=(Map<String, Object>) map.get("role");
				sm.SELECT("u.*")
				.FROM(new UserEntity(), "u")
				.WHERE("u.id IN(SELECT ru.idUser FROM sys_role_user ru WHERE ru.idRole=?)",role.get("id"));
				if(keywords!=null){
					sm.WHERE("u.id LIKE '%?%' OR u.username LIKE '%?%' OR u.nickname LIKE '%?%'".replaceAll("\\?", keywords));
				}
			}
		});
	}
	@Override
	public Map<String, Object> findUsersNotInRoleByPage(Map<String, Object> map) {
		return byPage(map, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				String keywords=(String) map.get("keywords");
				@SuppressWarnings("unchecked")
				Map<String, Object>role=(Map<String, Object>) map.get("role");
				sm.SELECT("u.*")
				.FROM(new UserEntity(), "u")
				.WHERE("u.id NOT IN(SELECT ru.idUser FROM sys_role_user ru WHERE ru.idRole=?)",role.get("id"));
				if(keywords!=null){
					sm.AND("u.id LIKE '%?%' OR u.username LIKE '%?%' OR u.nickname LIKE '%?%'".replaceAll("\\?", keywords));
				}
			}
		});
	}
	@Override
	public boolean addUserToRole(Map<String, Map<String, Object>> map) {
		Map<String, Object>user=map.get("user");
		Map<String, Object>role=map.get("role");
		Object idUser=user.get("id");
		Object idRole=role.get("id");
		RoleUserEntity ru=new RoleUserEntity();
		ru.setIdUser(String.valueOf(idUser));
		ru.setIdRole(String.valueOf(idRole));
		Object id=roleUserDao.save(ru);
		return id==null?false:true;
	}
	@Override
	public boolean removeUserFromRole(Map<String, Map<String, Object>> map) {
		Map<String, Object>user=map.get("user");
		Map<String, Object>role=map.get("role");
		Object idUser=user.get("id");
		Object idRole=role.get("id");
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.DELETE(new RoleUserEntity(), "ru")
		.WHERE("ru.idUser=?", idUser)
		.AND("ru.idRole=?", idRole);
		int i=updateBySQLGod(sm);
		return i>0?true:false;
	}
	@Override
	public List<Map<String, Object>> findRolesWithWhiteListASChecked(Object id) {
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("r.*")
		.SELECT_AS("r.id=wl.idRole", "checked")
		.FROM(new RoleEntity(), "r")
		.LEFT_JOIN(new WhiteListEntity(), "wl", "(wl.idRole=r.id AND wl.idTarget=?)",id);
		return roleDao.findBySQLGod(sm);
	}
}
