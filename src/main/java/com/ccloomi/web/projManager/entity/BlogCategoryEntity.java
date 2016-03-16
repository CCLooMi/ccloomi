package com.ccloomi.web.projManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogCategoryEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月16日-下午9:35:50
 */
@Table(name="t_b_category")
public class BlogCategoryEntity extends IdEntity{
	private static final long serialVersionUID = -8876931221808872138L;
	/**用户ID*/
	private String idUser;
	/**分类名称*/
	private String name;
	/**获取 idUser*/
	public String getIdUser() {
		return idUser;
	}
	/**设置 idUser*/
	public void setIdUser(String idUser) {
		this.idUser = idUser;
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
