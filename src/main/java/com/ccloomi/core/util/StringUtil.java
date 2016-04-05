package com.ccloomi.core.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 类名：StringUtil
 * 描述：字符串工具类
 * 作者： Chenxj
 * 日期：2015年6月18日 - 下午4:40:44
 */
public class StringUtil {

	/**
	 * 方法描述：字符串格式化
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午4:50:07
	 * @param f
	 * @param obj
	 * @return StringBuilder
	 */
	public static StringBuilder format(StringBuilder f,Object...objs){
		return format2(f, objs);
	}
	/**
	 * 描述：字符串格式化
	 * 作者：Chenxj
	 * 日期：2015年9月23日 - 下午11:24:39
	 * @param f
	 * @param objs
	 * @return
	 */
	public static StringBuilder format2(StringBuilder f,Object[]objs){
		StringBuilder sb=new StringBuilder();
		int count=0;
		int flag=0;
		char a="?".charAt(0);
		String b="";
		for(int i=0;i<f.length();i++){
			if(a==f.charAt(i)){
				String s=objs[count]==null?"":objs[count].toString();
				f.replace(i,i+1,b);
				sb.append(f.substring(flag, i)).append(s);
				flag=i;
				count++;
			}
		}
		sb.append(f.substring(flag, f.length()));
		return sb;
	}
	/**
	 * 方法描述：字符串格式化
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午4:58:56
	 * @param string
	 * @param objs
	 * @return String
	 */
	public static String format(String string,Object...objs){
		return format(new StringBuilder(string), objs).toString();
	}
	/**
	 * 描述：字符串格式化
	 * 作者：Chenxj
	 * 日期：2015年9月23日 - 下午11:24:30
	 * @param string
	 * @param objs
	 * @return
	 */
	public static String format2(String string,Object[]objs){
		return format(new StringBuilder(string), objs).toString();
	}
	/**
	 * 方法描述：字符串连接
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午5:20:32
	 * @param s
	 * @param strs
	 * @return StringBuilder
	 */
	public static StringBuilder join(String s,String...strs){
		StringBuilder sb=new StringBuilder();
		int l=strs.length;
		if(l>0){
			sb.append(strs[0]);
			for(int i=1;i<l;i++){
				sb.append(s).append(strs[i]);
			}
			return sb;
		}else{
			return sb;
		}
	}
	/**
	 * 方法描述：字符串连接
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午5:20:32
	 * @param s
	 * @param objs
	 * @return StringBuilder
	 */
	public static StringBuilder join(String s,Object...objs){
		return joinArray(s,objs);
	}
	public static StringBuilder joinArray(String s,Object[]objs){
		StringBuilder sb=new StringBuilder();
		int l=objs.length;
		if(l>0){
			Object obj0=objs[0];
			if(obj0 instanceof Object[]){
				sb.append("[").append(join(s,(Object[])obj0)).append("]");
			}else{
				sb.append(objs[0]);
			}
			for(int i=1;i<l;i++){
				Object obji=objs[i];
				if(obji instanceof Object[]){
					sb.append(s).append("[").append(join(s, (Object[])obji)).append("]");
				}else{
					sb.append(s).append(objs[i]);
				}
			}
			return sb;
		}else{
			return sb;
		}
	}
	public static StringBuilder joinCollection(String s,Collection<? extends Object>list){
		return joinArray(s,list.toArray());
	}
	/**
	 * 方法描述：连接字符串数组
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午5:22:01
	 * @param s
	 * @param objs
	 * @return String
	 */
	public static String joinString(String s,Object...objs){
		return join(s, objs).toString();
	}
	public static String joinArrayString(String s,Object[]objs){
		return joinArray(s,objs).toString();
	}
	public static String joinCollectionString(String s,Collection<? extends Object>list){
		return joinArray(s,list.toArray()).toString();
	}
	/**
	 * 方法描述：连接字符串数组
	 * 作者：Chenxj
	 * 日期：2015年6月18日 - 下午5:22:01
	 * @param s
	 * @param strs
	 * @return String
	 */
	public static String joinString(String s,String...strs){
		return join(s, strs).toString();
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年10月22日 - 下午9:32:13
	 * @return
	 */
	public static final String buildUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}
	/**
	 * 方法描述：首字母大写
	 * 作        者：Chenxj
	 * 日        期：2015年11月20日-上午10:43:04
	 * @param str
	 * @return
	 */
	public static String upperCaseFirstLatter(String str){
		char[] strChar=str.toCharArray();
		strChar[0]-=32;
		return String.valueOf(strChar);
	}
	/**
	 * 描述：String的splite方法有空字符串合并问题
	 * 作者：Chenxj
	 * 日期：2016年4月5日 - 下午10:31:13
	 * @param regex
	 * @param input
	 * @return
	 */
	public static List<String> splite(String regex,String input){
		Pattern pattern=Pattern.compile(regex);
		 int index = 0;
	        ArrayList<String> matchList = new ArrayList<>();
	        Matcher m = pattern.matcher(input);
	        // Add segments before each match found
	        while(m.find()) {
	        	if(index!=m.start()){
	        		matchList.add(input.substring(index, m.start()));
	        		index=m.start();
	        	}
                matchList.add(m.group());
                index = m.end();
	        }
	        // If no match was found, return this
	        if (index == 0){
	        	return Arrays.asList(input);
	        }
	        return matchList;
	}
	public static void main(String[] args) {
		String h="<body><div></div><span>123</span><span>abc</span></body>";
		System.out.println(splite("<[^<>]+>",h));
	}
}
