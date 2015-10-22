package com.ccloomi.core.common.bean;
/**
 * Copyright (c) 2015 CCLooMi.Inc
 * 类    名：Message
 * 类 描 述：和web端交互message
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年6月27日-上午9:20:04
 */
public class Message extends BaseBean{
	private static final long serialVersionUID = 3237437916356473926L;
	private String code;
	private String info;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	
}
