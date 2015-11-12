package com.ccloomi.web.system.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.system.entity.RoleEntity;
import com.ccloomi.web.system.service.PermissionService;

/**深圳市设计同道技术有限公司
 * 类    名：PermissionController
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年11月12日-下午2:17:00
 */
@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController{
	
	@Autowired
	private PermissionService permissionService;

	@RequestMapping("/jstree")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>viewsTreeByRoleId(@RequestBody RoleEntity role){
		Map<String, Object>map=new HashMap<>();
		map.put("data", permissionService.findpermissionsTreeByRoleId(role.getId()));
		map.put("ids", permissionService.findpermissionIdsByRoleId(role.getId()));
		return map;
	}
}
