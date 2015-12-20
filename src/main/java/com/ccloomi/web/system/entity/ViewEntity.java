package com.ccloomi.web.system.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ViewEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午10:44:46
 */
@Table(name="sys_view")
public class ViewEntity extends IdEntity{
	private static final long serialVersionUID = -262938732944816167L;
	private String name;
	private String url;
	private String pid;
	private String iconClass;
	private String type;
	private String idRoot;
	private int deepIndex;
	private int orderIndex;
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 url*/
	public String getUrl() {
		return url;
	}
	/**设置 url*/
	public void setUrl(String url) {
		this.url = url;
	}
	/**获取 pid*/
	public String getPid() {
		return pid;
	}
	/**设置 pid*/
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**获取 iconClass*/
	public String getIconClass() {
		return iconClass;
	}
	/**设置 iconClass*/
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	/**获取 type*/
	public String getType() {
		return type;
	}
	/**设置 type*/
	public void setType(String type) {
		this.type = type;
	}
	/**获取 idRoot*/
	public String getIdRoot() {
		return idRoot;
	}
	/**设置 idRoot*/
	public void setIdRoot(String idRoot) {
		this.idRoot = idRoot;
	}
	/**获取 deepIndex*/
	public int getDeepIndex() {
		return deepIndex;
	}
	/**设置 deepIndex*/
	public void setDeepIndex(int deepIndex) {
		this.deepIndex = deepIndex;
	}
	/**获取 orderIndex*/
	public int getOrderIndex() {
		return orderIndex;
	}
	/**设置 orderIndex*/
	public void setOrderIndex(int orderIndex) {
		this.orderIndex = orderIndex;
	}
}
