package com.ccloomi.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
		String regex="<[^<>]+>";
		String[] htmls=html.split(regex);
		String[] temps=template.split(regex);
		
		Pattern pattern=Pattern.compile(regex);
		Matcher matcher=pattern.matcher(template);
		List<String>tempMatchers=new ArrayList<String>();
		while(matcher.find()){
			tempMatchers.add(matcher.group());
		}
		for(int i=0;i<temps.length;i++){
			if("".equals(temps[i])){
				temps[i]=tempMatchers.remove(0);
			}
		}
		
		matcher=pattern.matcher(html);
		List<String>htmlMatchers=new ArrayList<String>();
		while(matcher.find()){
			htmlMatchers.add(matcher.group());
		}
		for(int i=0;i<htmls.length;i++){
			if("".equals(htmls[i])){
				htmls[i]=htmlMatchers.remove(0);
			}
		}
		
		List<String>tempList=Arrays.asList(temps);
		List<String>htmlList=Arrays.asList(htmls);
		
		for(int i=0,j=0;i<tempList.size();i++){
			String tp=tempList.get(i);
			String ht=htmlList.get(j);
			if(tp.matches(regex)){
				while(!tp.equals(ht)){
					j++;
					ht=htmlList.get(j);
				}
			}else{
				map.put(tp, ht);
			}
			j++;
		}
		return map;
	}
}
