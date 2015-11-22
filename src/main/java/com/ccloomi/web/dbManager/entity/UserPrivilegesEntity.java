package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserPrivilegesEntity
 * 类 描 述：用户权限
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午9:30:27
 */
@Table(name="INFORMATION_SCHEMA.USER_PRIVILEGES")
public class UserPrivilegesEntity extends BaseEntity{
	private static final long serialVersionUID = 4408958220078044247L;
	private String grantee;
	private String table_catalog;
	private String privilege_type;
	private String is_grantable;
	/**获取 grantee*/
	public String getGrantee() {
		return grantee;
	}
	/**设置 grantee*/
	public void setGrantee(String grantee) {
		this.grantee = grantee;
	}
	/**获取 table_catalog*/
	public String getTable_catalog() {
		return table_catalog;
	}
	/**设置 table_catalog*/
	public void setTable_catalog(String table_catalog) {
		this.table_catalog = table_catalog;
	}
	/**获取 privilege_type*/
	public String getPrivilege_type() {
		return privilege_type;
	}
	/**设置 privilege_type*/
	public void setPrivilege_type(String privilege_type) {
		this.privilege_type = privilege_type;
	}
	/**获取 is_grantable*/
	public String getIs_grantable() {
		return is_grantable;
	}
	/**设置 is_grantable*/
	public void setIs_grantable(String is_grantable) {
		this.is_grantable = is_grantable;
	}
}
