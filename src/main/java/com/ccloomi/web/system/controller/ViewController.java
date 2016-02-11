package com.ccloomi.web.system.controller;

import java.util.HashMap;
import java.util.List;
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
import com.ccloomi.web.system.entity.ViewEntity;
import com.ccloomi.web.system.service.ViewService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ViewController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月6日-下午10:35:58
 */
@Controller
@RequestMapping("/view")
public class ViewController extends BaseController{
	@Autowired
	private ViewService viewService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> viewsByPage(@RequestBody Map<String, Object>map){
		return viewService.findViewsByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody ViewEntity view){
		Object id=viewService.save(view);
		if(id==null){
			return responseMessageError("添加失败");
		}else{
			return responseMessageSuccess(view.getId());
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	@RequiresAuthentication
	public Message update(@RequestBody ViewEntity view){
		int i=viewService.update(view);
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("修改失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody ViewEntity view){
		int i=viewService.delete(view.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError(StringUtil.format("删除[?]失败", view.getName()));
		}
	}
	@RequestMapping("/jstree")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>viewsTreeByRoleId(@RequestBody RoleEntity role){
		Map<String, Object>map=new HashMap<>();
		map.put("data", viewService.findViewsTreeByRoleId(role.getId()));
		map.put("ids", viewService.findViewIdsByRoleId(role.getId()));
		return map;
	}
	@RequestMapping("/vis")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>visViews(){
		return viewService.findVisViews();
	}
	@RequestMapping("/tree")
	@ResponseBody
	@RequiresAuthentication
	public List<Map<String, Object>>viewsTree(){
		return viewService.findViewsTree();
	}
}
