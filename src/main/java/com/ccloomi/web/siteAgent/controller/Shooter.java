package com.ccloomi.web.siteAgent.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccloomi.web.system.constant.DDConstant;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Shooter
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月21日-下午4:02:37
 */
@Controller
@RequestMapping("/shooter")
public class Shooter extends BaseSiteAgentController{
	
	private String url=DDConstant.shooterMap().get("api-url");
	private String token=DDConstant.shooterMap().get("token");
	
	@Override
	public void beforRequest(Map<String, String> map) {
		map.put("token", token);
	}
	
	@Override
	public void beforResponse(Map<String, Object> map, Map<String, Object> responseMap) {
		responseMap=map;
	}
	
	@Override
	public String getUrl(Map<String, String> map) {
		String method=map.remove("method");
		if(method==null){
			method="";
		}
		return url+method;
	}
}
