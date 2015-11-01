package com.ccloomi.web.system.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SystemController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月26日-下午10:47:16
 */
@Controller
@RequestMapping("/sys")
public class SystemController extends BaseController{
	
	@RequestMapping("/login")
	@ResponseBody
	public Message login(@RequestBody Map<String, String>map){
		UsernamePasswordToken token=new UsernamePasswordToken(map.get("username"),map.get("password"));
		Subject sub=SecurityUtils.getSubject();
		try{
			sub.login(token);
			return responseMessageSuccess();
		}catch(Exception e){
			log.error("登录异常", e);
			return responseMessageError("登录异常");
		}
	}
	@RequestMapping("/currentUser")
	@ResponseBody
	@RequiresAuthentication
	public Object currentUser(){
		return SecurityUtils.getSubject().getSession().getAttribute("user");
	}
}
