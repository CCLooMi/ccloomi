package com.ccloomi.web.dbManager.entity;

import java.math.BigInteger;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProcessListEntity
 * 类 描 述：数据库连接进程列表
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午7:54:16
 */
@Table(name="INFORMATION_SCHEMA.PROCESSLIST")
public class ProcessListEntity extends BaseEntity{
	private static final long serialVersionUID = -6116433447979548755L;
	private BigInteger id;
	private String user;
	private String host;
	private String db;
	private String command;
	private int time;
	private String state;
	private String info;
	/**获取 id*/
	public BigInteger getId() {
		return id;
	}
	/**设置 id*/
	public void setId(BigInteger id) {
		this.id = id;
	}
	/**获取 user*/
	public String getUser() {
		return user;
	}
	/**设置 user*/
	public void setUser(String user) {
		this.user = user;
	}
	/**获取 host*/
	public String getHost() {
		return host;
	}
	/**设置 host*/
	public void setHost(String host) {
		this.host = host;
	}
	/**获取 db*/
	public String getDb() {
		return db;
	}
	/**设置 db*/
	public void setDb(String db) {
		this.db = db;
	}
	/**获取 command*/
	public String getCommand() {
		return command;
	}
	/**设置 command*/
	public void setCommand(String command) {
		this.command = command;
	}
	/**获取 time*/
	public int getTime() {
		return time;
	}
	/**设置 time*/
	public void setTime(int time) {
		this.time = time;
	}
	/**获取 state*/
	public String getState() {
		return state;
	}
	/**设置 state*/
	public void setState(String state) {
		this.state = state;
	}
	/**获取 info*/
	public String getInfo() {
		return info;
	}
	/**设置 info*/
	public void setInfo(String info) {
		this.info = info;
	}
}
