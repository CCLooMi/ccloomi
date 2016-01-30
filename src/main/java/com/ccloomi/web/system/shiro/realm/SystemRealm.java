package com.ccloomi.web.system.shiro.realm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.web.system.entity.PermissionEntity;
import com.ccloomi.web.system.entity.RoleEntity;
import com.ccloomi.web.system.entity.UserEntity;
import com.ccloomi.web.system.service.RoleService;
import com.ccloomi.web.system.service.UserService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemRealm
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月27日-下午9:27:55
 */
public class SystemRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@SuppressWarnings("unchecked")
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//		Object idUser=principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		Map<String, Object>userMap=(Map<String, Object>)SecurityUtils.getSubject().getSession().getAttribute("user");
		info.addRoles((List<String>)userMap.get("roles"));
		info.addStringPermissions((List<String>)userMap.get("permissions"));
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;
		UserEntity user=userService.findByUsernameAndPassword(utoken.getUsername(), String.valueOf(utoken.getPassword()));
		if(user!=null){
			List<String>roles=new ArrayList<>();
			List<String>permissions=new ArrayList<>();
			List<RoleEntity>rs=roleService.findRolesByIdUser(user.getId());
			List<PermissionEntity>ps=roleService.findPermissionsByIdUser(user.getId());
			for(RoleEntity r:rs){
				roles.add(r.getCode());
			}
			for(PermissionEntity p:ps){
				permissions.add(p.getCode());
			}
			//保存用户基本信息
			Map<String, Object>userMap=new HashMap<>();
			userMap.put("user", user);
			userMap.put("views", roleService.findViewsByIdUser(user.getId()));
			userMap.put("roles", roles);
			userMap.put("permissions", permissions);
			SecurityUtils.getSubject().getSession().setAttribute("user", userMap);
			SecurityUtils.getSubject().getSession().setAttribute("roles", rs);
			SecurityUtils.getSubject().getSession().setAttribute("permissions", ps);
			return new SimpleAuthenticationInfo(user.getId(),user.getPassword(),user.getUsername());
		}else{
			throw new UnknownAccountException();
		}
	}

}
