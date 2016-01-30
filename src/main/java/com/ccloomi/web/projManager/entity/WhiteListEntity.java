package com.ccloomi.web.projManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：WhiteListEntity
 * 类 描 述：白名单实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月30日-下午9:30:22
 */
@Table(name="t_cc_white_list")
public class WhiteListEntity extends IdEntity{
	private static final long serialVersionUID = -678787841913574536L;
	/**0产品1项目*/
	private Integer type;
	/**目标ID*/
	private String idTarget;
	/**角色ID*/
	private String idRole;
	/**获取 type*/
	public Integer getType() {
		return type;
	}
	/**设置 type*/
	public void setType(Integer type) {
		this.type = type;
	}
	/**获取 idTarget*/
	public String getIdTarget() {
		return idTarget;
	}
	/**设置 idTarget*/
	public void setIdTarget(String idTarget) {
		this.idTarget = idTarget;
	}
	/**获取 idRole*/
	public String getIdRole() {
		return idRole;
	}
	/**设置 idRole*/
	public void setIdRole(String idRole) {
		this.idRole = idRole;
	}
}
