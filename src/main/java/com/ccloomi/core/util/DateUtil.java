package com.ccloomi.core.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DateUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月29日-下午9:18:58
 */
public class DateUtil {
	/**
	 * 描述：String转date,出错则返回null
	 * 格式1：yyyy-MM-dd HH:mm|yyyy-MM-ddHH:mm|yyyy-MM-dd HHmm|yyyy-MM-ddHHmm
	 * 格式2：yyyy/MM/dd HH:mm|yyyy/MM/ddHH:mm|yyyy/MM/dd HHmm|yyyy/MM/ddHHmm
	 * 作者：Chenxj
	 * 日期：2016年3月29日 - 下午9:32:34
	 * @param dateString
	 * @return
	 */
	public static Date dateFromString(String dateString){
		try{
			SimpleDateFormat simpleDateFormat;
			if(dateString.contains("-")){
				if(dateString.contains(" ")){
					if(dateString.contains(":")){
						simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm");
						return simpleDateFormat.parse(dateString);
					}else{
						simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HHmm");
						return simpleDateFormat.parse(dateString);
					}
				}else if(dateString.length()>10){
					if(dateString.contains(":")){
						simpleDateFormat=new SimpleDateFormat("yyyy-MM-ddHH:mm");
						return simpleDateFormat.parse(dateString);
					}else{
						simpleDateFormat=new SimpleDateFormat("yyyy-MM-ddHHmm");
						return simpleDateFormat.parse(dateString);
					}
				}else{
					simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
					return simpleDateFormat.parse(dateString);
				}
			}else if(dateString.contains("/")){
				if(dateString.contains(" ")){
					if(dateString.contains(":")){
						simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HH:mm");
						return simpleDateFormat.parse(dateString);
					}else{
						simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd HHmm");
						return simpleDateFormat.parse(dateString);
					}
				}else if(dateString.length()>10){
					if(dateString.contains(":")){
						simpleDateFormat=new SimpleDateFormat("yyyy/MM/ddHH:mm");
						return simpleDateFormat.parse(dateString);
					}else{
						simpleDateFormat=new SimpleDateFormat("yyyy/MM/ddHHmm");
						return simpleDateFormat.parse(dateString);
					}
				}else{
					simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
					return simpleDateFormat.parse(dateString);
				}
			}else{
				return null;
			}
		}catch(Exception e){
			return null;
		}
	}
	public static void main(String[] args) {
		Date d1=DateUtil.dateFromString("2009-01-021212");
		Date d2=DateUtil.dateFromString("2009-01-021200");
		System.out.println(d2.after(d1));
		System.out.println(d2.before(d1));
	}
}
