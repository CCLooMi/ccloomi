package com.sql.entity;

import com.sql.annotation.MappedSuperclass;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：IdEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午8:44:30
 */
@MappedSuperclass
public class IdEntity extends BaseEntity{
	private static final long serialVersionUID = -39620986413307929L;
	private Object id;
	/**获取 id*/
	public Object getId() {
		return id;
	}
	/**设置 id*/
	public void setId(Object id) {
		this.id = id;
	}
}
