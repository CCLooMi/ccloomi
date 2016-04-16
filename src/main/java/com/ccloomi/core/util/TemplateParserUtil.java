package com.ccloomi.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
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
public class TemplateParserUtil {
	
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
	public static String cleanHtml(String html){
		File f=new File("C:/Users/Idccapp25/Desktop/cp.html");
		StringBuilder sb=new StringBuilder();
		try {
			InputStreamReader read=new InputStreamReader(new FileInputStream(f),Charset.forName("GBK"));
			BufferedReader br=new BufferedReader(read);
			String line=null;
			while((line=br.readLine())!=null){
				sb.append(line);
			}
			br.close();
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
