package com.ccloomi.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：HttpUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月17日-下午8:29:48
 */
public class HttpUtil {
	public static String getHtmlAsString(String url){
		return getHtmlAsString(url,null);
	}
	public static String getHtmlAsString(String url,Map<String, String>args){
		CloseableHttpClient client=HttpClients.createDefault();
		StringBuilder sb=new StringBuilder();
		try{
			URIBuilder uriBuilder=new URIBuilder(url);
			if(args!=null&&!args.isEmpty()){
				for(String key:args.keySet()){
					uriBuilder.setParameter(key, args.get(key));
				}
			}
			URI uri=uriBuilder.build();
			HttpGet httpGet=new HttpGet(uri);
			CloseableHttpResponse resp = client.execute(httpGet);
			HttpEntity entity=resp.getEntity();
			InputStream input=entity.getContent();
			InputStreamReader read=new InputStreamReader(input,Charset.forName("GBK"));
			BufferedReader br=new BufferedReader(read);
			String textLine=null;
			while((textLine=br.readLine())!=null){
				sb.append(textLine);
			}
			input.close();
			resp.close();
		}catch(Exception e){
			return "";
		}
		return sb.toString();
	}
}
