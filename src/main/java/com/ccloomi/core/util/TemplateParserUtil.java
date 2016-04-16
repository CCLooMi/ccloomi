package com.ccloomi.core.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 类名：TemplateParserUtil
 * 描述：
 * 作者： Chenxj
 * 日期：2016年4月5日 - 下午3:56:00
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class TemplateParserUtil {
	private static Logger log=LoggerFactory.getLogger(TemplateParserUtil.class);
	/**
	 * 方法描述：html模板逆向到map对象
	 * 作者：Chenxj
	 * 日期：2016年4月5日 - 下午3:56:16
	 * @param html html文件内容
	 * @param template 生成该html内容的模板
	 * @return 模板数据对象
	 */
	public static Map<String, String> parserHtmlTemplate2Map(String html,String template){
		Map<String, String>map=new HashMap<String, String>();
		/**匹配html标签*/
		String regex="<[^<>]+>";
		/**用于匹配参数*/
		String argRegex="\\{[^\\{\\}]+\\}";
		
		List<String>tempList=StringUtil.splite(regex, template);
		List<String>htmlList=StringUtil.splite(regex, html);
		
		for(int i=0,j=0;i<tempList.size();i++){
			String tp=tempList.get(i);
			String ht=htmlList.get(j);
			if(tp.matches(regex)){
				while(!tp.equals(ht)){
					j++;
					ht=htmlList.get(j);
				}
			}else if(tp.matches(argRegex)){
				map.put(tp.substring(1, tp.length()-1), ht);
			}
			j++;
		}
		return map;
	}
	/**
	 * 方法描述：从输入流逆向到map对象
	 * 作者：Chenxj
	 * 日期：2016年4月16日 - 下午3:07:32
	 * @param inputStream
	 * @param template
	 * @return
	 */
	public static Map<String, String>parserHtmlTemplate2MapFromInputStream(InputStream inputStream,String template){
		StringBuilder sb=new StringBuilder();
		try {
			InputStreamReader read=new InputStreamReader(inputStream,Charset.forName("GBK"));
			BufferedReader br=new BufferedReader(read);
			String line=null;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			br.close();
			return parserHtmlTemplate2Map(sb.toString(), template);
		} catch (Exception e) {
			log.error("从输入流逆向到map对象失败", e);
		}
		return new HashMap<>();
	}
}
