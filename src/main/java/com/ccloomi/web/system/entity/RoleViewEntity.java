package com.ccloomi.web.system.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleViewEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午11:13:28
 */
@Table(name="sys_role_view")
public class RoleViewEntity extends IdEntity{
	private static final long serialVersionUID = -1107942575015991700L;
	private String idRole;
	private String idView;
	/**获取 idRole*/
	public String getIdRole() {
		return idRole;
	}
	/**设置 idRole*/
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
	/**获取 idView*/
	public String getIdView() {
		return idView;
	}
	/**设置 idView*/
	public void setIdView(String idView) {
		this.idView = idView;
	}
}
