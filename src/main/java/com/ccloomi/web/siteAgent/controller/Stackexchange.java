package com.ccloomi.web.siteAgent.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ccloomi.web.system.constant.DDConstant;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Stackexchange
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月20日-下午4:42:13
 */
@Controller
@RequestMapping("/stackexchange")
public class Stackexchange extends BaseSiteAgentController{
	private String url=DDConstant.stackexchangeMap().get("api-url");

	@Override
	public void beforRequest(Map<String, String> map) {
		String page=map.remove("pageNumber");
		String pagesize=map.remove("pageSize");
		if(page!=null)map.put("page", page);
		if(pagesize!=null)map.put("pagesize", pagesize);
	}

	@Override
	public void beforResponse(Map<String, Object> map, Map<String, Object> responseMap) {
		// TODO Auto-generated method stub
		for(String key:map.keySet()){
			responseMap.put(key, map.get(key));
		}
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
