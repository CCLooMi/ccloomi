package com.ccloomi.web.system.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：PermissionEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午10:51:35
 */
@Table(name="sys_permission")
public class PermissionEntity extends IdEntity{
	private static final long serialVersionUID = -666571342834345761L;
	private String code;
	private String name;
	/**获取 code*/
	public String getCode() {
		return code;
	}
	/**设置 code*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
}
