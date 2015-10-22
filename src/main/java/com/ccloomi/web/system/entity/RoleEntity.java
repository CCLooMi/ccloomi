package com.ccloomi.web.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleEntity
 * 类 描 述：角色实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午9:28:24
 */
@Entity
@Table(name="sys_role")
public class RoleEntity extends IdEntity{
	private static final long serialVersionUID = -1797926613295467013L;
	/**角色编码*/
	private String code;
	/**角色名称*/
	private String name;
	/**获取 角色编码*/
	public String getCode() {
		return code;
	}
	/**设置 角色编码*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取 角色名称*/
	public String getName() {
		return name;
	}
	/**设置 角色名称*/
	public void setName(String name) {
		this.name = name;
	}
	
}
