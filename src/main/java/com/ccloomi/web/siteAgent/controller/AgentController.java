package com.ccloomi.web.siteAgent.controller;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.controller.BaseController;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AgentController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月23日-下午9:01:03
 */
@Controller
@RequestMapping("/site")
public class AgentController extends BaseController{
	@RequestMapping("/agent")
	@ResponseBody
	public String agent(@RequestParam Map<String, String>map) throws Exception{
		String site=map.get("site");
//		URIBuilder builder=new URIBuilder();
//		for(String p:map.keySet()){
//			if("scheme".equals(p)){
//				builder.setScheme(map.get(p));
//			}else if("host".equals(p)){
//				builder.setHost(map.get(p));
//			}else if("path".equals(p)){
//				builder.setPath(map.get(p));
//			}else{
//				builder.setParameter(p, map.get(p));
//			}
//		}
//		HttpGet get=new HttpGet(builder.build());
		CloseableHttpClient client=HttpClients.createDefault();
		HttpGet get=new HttpGet(site);
		CloseableHttpResponse response=client.execute(get);
		HttpEntity entity=response.getEntity();
		//处理返回结果
		InputStream in = entity.getContent();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = in.read(buffer)) != -1) {
			baos.write(buffer, 0, len);
		}
		//释放资源
		EntityUtils.consume(entity);
		client.close();
		response.close();
		return baos.toString();
	}
	public static void main(String[] args) throws Exception {
		Map<String, String>map=new HashMap<>();
		map.put("site", "http://mvnrepository.com/search?q=spring");
		System.out.println(new AgentController().agent(map));
	}
}
