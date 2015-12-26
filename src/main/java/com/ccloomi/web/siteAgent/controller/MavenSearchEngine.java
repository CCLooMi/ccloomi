package com.ccloomi.web.siteAgent.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private String url2="http://repo1.maven.org/maven2/";
	private CloseableHttpClient client=HttpClients.createDefault();
	private ObjectMapper objMapper;
	@SuppressWarnings("unchecked")
	@RequestMapping("/maven")
	@ResponseBody
	public Object doAgent(@RequestBody Map<String, Object>map) throws Exception{
		Map<String, Object>responseMap;
		URIBuilder uriBuilder=new URIBuilder(url);
		for(String key:map.keySet()){
			if("start".equals(key)){
				uriBuilder.setParameter(key, String.valueOf((Integer)(map.get(key))-1));
			}else{
				uriBuilder.setParameter(key, String.valueOf(map.get(key)));
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
	@RequestMapping("/maven/download")
	public void doDownload(@RequestParam Map<String, String>map,HttpServletResponse response) throws Exception{
		String g=map.get("g").replaceAll("\\.", "/");
		String a=map.get("a");
		String v=map.get("v");
		String p=map.get("p");
		
		String path=g+"/"+a+"/"+v+"/";
		String file=a+"-"+v+p;
		URIBuilder uriBuilder=new URIBuilder(url2+path+file);
		
		URI uri=uriBuilder.build();
		log.debug("MavenDownload URL::[{}]",uri);
		HttpGet httpGet=new HttpGet(uri);
		CloseableHttpResponse resp = client.execute(httpGet);
		HttpEntity entity=resp.getEntity();
		response.setContentType(entity.getContentType().getValue());
		response.setContentLengthLong(entity.getContentLength());
		response.setHeader("Content-Disposition", "attachment; filename="+file);
		System.out.println(entity.getContentType().getValue());
		InputStream input=entity.getContent();
		OutputStream out=response.getOutputStream();
		byte[]bytes=new byte[1024];
		while(input.read(bytes)!=-1){
			out.write(bytes);
		}
		out.flush();
		input.close();
		resp.close();
	}
}
