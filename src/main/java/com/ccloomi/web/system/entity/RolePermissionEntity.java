package com.ccloomi.web.system.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RolePermissionEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午10:53:43
 */
@Table(name="sys_role_permission")
public class RolePermissionEntity extends IdEntity{
	private static final long serialVersionUID = -5583705527758667419L;
	private String idRole;
	private String idPermission;
	/**获取 idRole*/
	public String getIdRole() {
		return idRole;
	}
	/**设置 idRole*/
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	/**获取 idPermission*/
	public String getIdPermission() {
		return idPermission;
	}
	/**设置 idPermission*/
	public void setIdPermission(String idPermission) {
		this.idPermission = idPermission;
	}
}
