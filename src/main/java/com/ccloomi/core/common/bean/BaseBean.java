package com.ccloomi.core.common.bean;

import java.io.Serializable;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 类名：BaseBean
 * 描述：Bean基类
 * 作者： Chenxj
 * 日期：2015年6月23日 - 下午4:06:47
 */
public abstract class BaseBean implements Serializable{
	private static final long serialVersionUID = 5295720500778299760L;

	/**
	 * 方法描述：转化为JSON字符串
	 * 作        者：Chenxj
	 * 日        期：2015年6月23日-下午3:28:27
	 */
	@Override
	public String toString(){
		ObjectMapper om=new ObjectMapper();
		try {
			return om.writeValueAsString(this);
		} catch (Exception e) {
			return super.toString();
		}finally{
			om=null;
		}
	}
}
