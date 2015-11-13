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
import com.ccloomi.web.system.entity.UserEntity;
import com.ccloomi.web.system.service.UserService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月13日-下午8:45:16
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private UserService userService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> usersByPage(@RequestBody Map<String, Object>map){
		return userService.findUsersByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody UserEntity user){
		Object id=userService.save(user);
		if(id==null){
			return responseMessageError("添加失败");
		}else{
			return responseMessageSuccess(user.getId());
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	@RequiresAuthentication
	public Message update(@RequestBody UserEntity user){
		int i=userService.update(user);
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("修改失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody UserEntity user){
		int i=userService.delete(user.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError(StringUtil.format("删除[?]失败", user.getUsername()));
		}
	}
}
