package com.ccloomi.web.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleAuthorityEntity
 * 类 描 述：角色权限中间实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午9:28:59
 */
@Entity
@Table(name="sys_role_authority")
public class RoleAuthorityEntity extends IdEntity {
	private static final long serialVersionUID = 6126944267802362674L;
	/**角色ID*/
	private String idRole;
	/**权限ID*/
	private String idAuthority;
	/**获取 角色ID*/
	public String getIdRole() {
		return idRole;
	}
	/**设置 角色ID*/
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	/**获取 权限ID*/
	public String getIdAuthority() {
		return idAuthority;
	}
	/**设置 权限ID*/
	public void setIdAuthority(String idAuthority) {
		this.idAuthority = idAuthority;
	}
	
}
