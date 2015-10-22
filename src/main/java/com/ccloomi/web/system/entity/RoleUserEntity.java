package com.ccloomi.web.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleUserEntity
 * 类 描 述：角色用户实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午9:29:29
 */
@Entity
@Table(name="sys_role_user")
public class RoleUserEntity extends IdEntity{
	private static final long serialVersionUID = 814698415390937227L;
	/**角色ID*/
	private String idRole;
	/**用户ID*/
	private String idUser;
	/**获取 角色ID*/
	public String getIdRole() {
		return idRole;
	}
	/**设置 角色ID*/
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	/**获取 用户ID*/
	public String getIdUser() {
		return idUser;
	}
	/**设置 用户ID*/
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	
}
