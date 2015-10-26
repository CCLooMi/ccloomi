package com.ccloomi.web.system.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.system.service.UserService;

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
	@Autowired
	private UserService userService;
	
	@RequestMapping("/login")
	public Message login(Map<String, String>map){
		Message m=new Message();
		
		return m;
	}
}
