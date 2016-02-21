package com.ccloomi.web.siteAgent.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.system.constant.DDConstant;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Shooter
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月21日-下午4:02:37
 */
@Controller
public class Shooter extends BaseController{
	private String url=DDConstant.shooterMap().get("api-url");
	private String token=DDConstant.shooterMap().get("token");
	@RequestMapping("/shooter")
	@ResponseBody
	public Object doAgent(@RequestBody Map<String, Object>map) throws Exception {
		System.out.println(url+"?"+token);
		return DDConstant.shooterMap();
	}
}
