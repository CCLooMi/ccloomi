package com.ccloomi.web.system.shiro.realm;

import java.util.HashMap;
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
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

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
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Object idUser=principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		info.addRoles(roleService.findRolesByIdUser(idUser));
		info.addStringPermissions(roleService.findPermissionsByIdUser(idUser));
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken utoken=(UsernamePasswordToken) token;
		UserEntity user=userService.findByUsernameAndPassword(utoken.getUsername(), String.valueOf(utoken.getPassword()));
		if(user!=null){
			Map<String, Object>userMap=new HashMap<>();
			userMap.put("user", user);
			userMap.put("views", roleService.findViewsByIdUser(user.getId()));
			userMap.put("roles", roleService.findRolesByIdUser(user.getId()));
			userMap.put("permissions", roleService.findPermissionsByIdUser(user.getId()));
			
			Subject sub=SecurityUtils.getSubject();
			Session session=sub.getSession();
			session.setAttribute("user", userMap);
			return new SimpleAuthenticationInfo(user.getId(),user.getPassword(),user.getUsername());
		}else{
			throw new UnknownAccountException();
		}
	}

}
