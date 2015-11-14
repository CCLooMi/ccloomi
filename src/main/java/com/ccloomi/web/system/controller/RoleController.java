package com.ccloomi.web.system.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.system.entity.RoleEntity;
import com.ccloomi.web.system.service.RoleService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月8日-下午10:05:03
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController{
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> rolesByPage(@RequestBody Map<String, Object>map){
		return roleService.findRolesByPage(map);
	}
	@RequestMapping("/usersInRoleByPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> usersInRoleByPage(@RequestBody Map<String, Object>map){
		return roleService.findUsersInRoleByPage(map);
	}
	@RequestMapping("/usersNotInRoleByPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> usersNotInRoleByPage(@RequestBody Map<String, Object>map){
		return roleService.findUsersNotInRoleByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody RoleEntity role){
		Object id=roleService.save(role);
		if(id==null){
			return responseMessageError("添加失败");
		}else{
			return responseMessageSuccess(role.getId());
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	@RequiresAuthentication
	public Message update(@RequestBody RoleEntity role){
		int i=roleService.update(role);
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("修改失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody RoleEntity role){
		int i=roleService.delete(role.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError(StringUtil.format("删除[?]失败", role.getName()));
		}
	}
	@RequestMapping("/saveViewJstree")
	@ResponseBody
	@RequiresAuthentication
	public Message saveViewJstree(@RequestBody Map<String, Object>map){
		boolean isOK=roleService.saveViewJstreeData(map);
		if(isOK){
			return responseMessageSuccess();
		}else{
			return responseMessageError("保存数据失败");
		}
	}
	@RequestMapping("/savePermissionJstree")
	@ResponseBody
	@RequiresAuthentication
	public Message savePermissionJstree(@RequestBody Map<String, Object>map){
		boolean isOK=roleService.savePermissionJstreeData(map);
		if(isOK){
			return responseMessageSuccess();
		}else{
			return responseMessageError("保存数据失败");
		}
	}
}
