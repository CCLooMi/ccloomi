package com.ccloomi.web.siteAgent.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.system.constant.DDConstant;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Stackexchange
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月20日-下午4:42:13
 */
@Controller
public class Stackexchange extends BaseController{
	private String url=DDConstant.stackexchangeMap().get("api-url");
	@RequestMapping("/stackexchange")
	@ResponseBody
	public Object doAgent(@RequestBody Map<String, Object>map) throws Exception {
		System.out.println(url);
		return DDConstant.stackexchangeMap();
	}
}
