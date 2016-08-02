package com.sql.entity;

import com.sql.annotation.ExtendType;
import com.sql.annotation.Table;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：UserEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午9:08:05
 */
@Table(name="t_s_user")
public class UserEntity extends GenericEntity{
	private static final long serialVersionUID = -7934937705831905220L;
	private String loginName;
	private String password;
	@ExtendType
	private int userType;
	/**获取 loginName*/
	public String getLoginName() {
		return loginName;
	}
	/**设置 loginName*/
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	/**获取 password*/
	public String getPassword() {
		return password;
	}
	/**设置 password*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**获取 userType*/
	public int getUserType() {
		return userType;
	}
	/**设置 userType*/
	public void setUserType(int userType) {
		this.userType = userType;
	}
}
