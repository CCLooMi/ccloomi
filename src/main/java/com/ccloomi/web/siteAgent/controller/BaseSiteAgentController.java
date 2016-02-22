package com.ccloomi.web.siteAgent.controller;

import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 类名：BaseSiteAgentController
 * 描述：
 * 作者： Chenxj
 * 日期：2016年2月22日 - 下午5:10:46
 */
public abstract class BaseSiteAgentController extends BaseController{
	
	private CloseableHttpClient client=HttpClients.createDefault();
	private ObjectMapper objMapper=new ObjectMapper();
	
	@SuppressWarnings("unchecked")
	@RequestMapping("/doAgent")
	@ResponseBody
	public Object doAgent(@RequestBody Map<String, String>map) throws Exception {
		Map<String, Object>responseMap=new HashMap<>();
		URIBuilder uriBuilder=new URIBuilder(getUrl(map));
		beforRequest(map);
		for(String key:map.keySet()){
			uriBuilder.setParameter(key, map.get(key));
		}
		URI uri=uriBuilder.build();
		log.debug("{}/doAgent URL::[{}]",getRequestMapping(),uri);
		HttpGet httpGet=new HttpGet(uri);
		CloseableHttpResponse resp=client.execute(httpGet);
		HttpEntity entity=resp.getEntity();
		InputStream input=entity.getContent();
		Map<String, Object>jsonMap=objMapper.readValue(input, Map.class);
		beforResponse(jsonMap,responseMap);
		return responseMap;
	}
	private String getRequestMapping(){
		RequestMapping rm=getClass().getDeclaredAnnotation(RequestMapping.class);
		if(rm!=null){
			return rm.value()[0];
		}else{
			return "";
		}
	}
	/**
	 * 方法描述：发送请求之前
	 * 作者：Chenxj
	 * 日期：2016年2月22日 - 下午5:28:57
	 * @param map 请求map
	 */
	public abstract void beforRequest(Map<String, String>map);
	/**
	 * 方法描述：获取到数据返回结果之前
	 * 作者：Chenxj
	 * 日期：2016年2月22日 - 下午5:29:20
	 * @param map 返回结果map
	 * @param responseMap 响应map
	 */
	public abstract void beforResponse(Map<String, Object>map,Map<String, Object>responseMap);
	/**
	 * 方法描述：获取请求Url
	 * 作者：Chenxj
	 * 日期：2016年2月22日 - 下午5:29:53
	 * @param map 请求map
	 * @return
	 */
	public abstract String getUrl(Map<String, String>map);
}
