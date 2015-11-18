package com.ccloomi.web.siteAgent.controller;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;
import com.fasterxml.jackson.databind.ObjectMapper;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：MavenSearchEngine
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月17日-下午9:26:04
 */
@Controller
public class MavenSearchEngine extends BaseController{
	private String url="http://search.maven.org/solrsearch/select";
	private CloseableHttpClient client=HttpClients.createDefault();
	private ObjectMapper objMapper;
	@SuppressWarnings("unchecked")
	@RequestMapping("/maven")
	@ResponseBody
	public Object doAgent(@RequestBody Map<String, String>map) throws Exception{
		Map<String, Object>responseMap;
		URIBuilder uriBuilder=new URIBuilder(url);
		for(String key:map.keySet()){
			if("start".equals(key)){
				uriBuilder.setParameter(key, String.valueOf(Integer.valueOf(map.get(key))-1));
			}else{
				uriBuilder.setParameter(key, map.get(key));
			}
		}
		URI uri=uriBuilder.build();
		log.debug("MavenSearch URL::[{}]",uri);
		HttpGet httpGet=new HttpGet(uri);
		CloseableHttpResponse resp = client.execute(httpGet);
		HttpEntity entity=resp.getEntity();
		InputStream input=entity.getContent();
		objMapper = new ObjectMapper();
		Map<String, Object>jsonMap=objMapper.readValue(input,Map.class);
		input.close();
		resp.close();
		responseMap=(Map<String, Object>) jsonMap.get("response");
		responseMap.put("responseHeader", jsonMap.get("responseHeader"));
		return responseMap;
	}
}
