package com.ccloomi.web.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：AuthorityEntity
 * 类 描 述：权限实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月3日-下午9:26:52
 */
@Entity
@Table(name="sys_authority")
public class AuthorityEntity extends IdEntity {
	private static final long serialVersionUID = 5682057813392261492L;
	/**权限名称*/
	private String name;
	/**地址*/
	private String url;
	/**上级权限ID*/
	private String pid;
	/**权限图标ID*/
	private String idIcon;
	/**权限类型*/
	private String type;
	/**获取 权限名称*/
	public String getName() {
		return name;
	}
	/**设置 权限名称*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 地址*/
	public String getUrl() {
		return url;
	}
	/**设置 地址*/
	public void setUrl(String url) {
		this.url = url;
	}
	/**获取 上级权限ID*/
	public String getPid() {
		return pid;
	}
	/**设置 上级权限ID*/
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**获取 权限图标ID*/
	public String getIdIcon() {
		return idIcon;
	}
	/**设置 权限图标ID*/
	public void setIdIcon(String idIcon) {
		this.idIcon = idIcon;
	}
	/**获取 权限类型*/
	public String getType() {
		return type;
	}
	/**设置 权限类型*/
	public void setType(String type) {
		this.type = type;
	}
	
}
