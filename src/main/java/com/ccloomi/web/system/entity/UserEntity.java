package com.ccloomi.web.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.component.security.SecretUtil;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserEntity
 * 类 描 述：用户实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午9:29:46
 */
@Entity
@Table(name="sys_user")
public class UserEntity extends IdEntity{
	private static final long serialVersionUID = 9103939905620343523L;
	/**登录名*/
	private String username;
	/**密码*/
	private String password;
	/**昵称*/
	private String nickname;
	/**
	 * 描述：将密码MD5化
	 * 作者：Chenxj
	 * 日期：2015年8月11日 - 下午11:03:37
	 * @return
	 */
	public UserEntity md5Password(){
		if(this.password!=null){
			setPassword(SecretUtil.MD5(this.password));
		}
		return this;
	}
	/**获取 登录名*/
	public String getUsername() {
		return username;
	}
	/**设置 登录名*/
	public void setUsername(String username) {
		this.username = username;
	}
	/**获取 密码*/
	public String getPassword() {
		return password;
	}
	/**设置 密码*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**获取 昵称*/
	public String getNickname() {
		return nickname;
	}
	/**设置 昵称*/
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
