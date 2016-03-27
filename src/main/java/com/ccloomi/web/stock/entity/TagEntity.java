package com.ccloomi.web.stock.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TagEntity
 * 类 描 述：股票标签
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-下午2:15:42
 */
@Table(name="t_s_tag")
public class TagEntity extends IdEntity{
	private static final long serialVersionUID = -3276877444001997142L;
	private String name;

	/**获取 name*/
	public String getName() {
		return name;
	}

	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
}
