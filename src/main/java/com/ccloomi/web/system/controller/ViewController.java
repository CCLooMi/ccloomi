package com.ccloomi.web.system.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;
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
}
