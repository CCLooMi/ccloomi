package com.ccloomi.web.system.controller;

import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public Message login(Map<String, String>map){
		Message m=new Message();
		UsernamePasswordToken token=new UsernamePasswordToken("Chenxj","apple");
		Subject sub=SecurityUtils.getSubject();
		sub.login(token);
		System.out.println(sub.getPrincipal());
		System.out.println(sub.getPrincipals());
		System.out.println(sub.getSession());
		
		System.out.println(sub.hasRole("admin"));
		System.out.println(sub.isPermitted("add"));
		
		System.out.println(sub.getSession().getAttribute("user"));
		return m;
	}
}
