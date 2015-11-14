package com.ccloomi.web.system.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：IconEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月14日-下午8:17:14
 */
@Table(name="sys_icon")
public class IconEntity extends IdEntity{
	private static final long serialVersionUID = 436907929449362604L;
	private String groupName;
	private String className;
	/**获取 groupName*/
	public String getGroupName() {
		return groupName;
	}
	/**设置 groupName*/
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	/**获取 className*/
	public String getClassName() {
		return className;
	}
	/**设置 className*/
	public void setClassName(String className) {
		this.className = className;
	}
}
