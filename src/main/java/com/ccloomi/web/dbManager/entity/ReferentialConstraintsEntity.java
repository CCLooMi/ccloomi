package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ReferentialConstraintsEntity
 * 类 描 述：引用约束表
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午7:18:57
 */
@Table(name="INFORMATION_SCHEMA.REFERENTIAL_CONSTRAINTS")
public class ReferentialConstraintsEntity extends BaseEntity{
	private static final long serialVersionUID = -4732803501917759134L;
	private String constraint_catalog;
	private String constraint_schema;
	private String constraint_name;
	private String unique_constraint_catalog;
	private String unique_constraint_schema;
	private String unique_constraint_name;
	private String match_option;
	private String update_rule;
	private String delete_rule;
	private String table_name;
	private String referenced_table_name;
	/**获取 constraint_catalog*/
	public String getConstraint_catalog() {
		return constraint_catalog;
	}
	/**设置 constraint_catalog*/
	public void setConstraint_catalog(String constraint_catalog) {
		this.constraint_catalog = constraint_catalog;
	}
	/**获取 constraint_schema*/
	public String getConstraint_schema() {
		return constraint_schema;
	}
	/**设置 constraint_schema*/
	public void setConstraint_schema(String constraint_schema) {
		this.constraint_schema = constraint_schema;
	}
	/**获取 constraint_name*/
	public String getConstraint_name() {
		return constraint_name;
	}
	/**设置 constraint_name*/
	public void setConstraint_name(String constraint_name) {
		this.constraint_name = constraint_name;
	}
	/**获取 unique_constraint_catalog*/
	public String getUnique_constraint_catalog() {
		return unique_constraint_catalog;
	}
	/**设置 unique_constraint_catalog*/
	public void setUnique_constraint_catalog(String unique_constraint_catalog) {
		this.unique_constraint_catalog = unique_constraint_catalog;
	}
	/**获取 unique_constraint_schema*/
	public String getUnique_constraint_schema() {
		return unique_constraint_schema;
	}
	/**设置 unique_constraint_schema*/
	public void setUnique_constraint_schema(String unique_constraint_schema) {
		this.unique_constraint_schema = unique_constraint_schema;
	}
	/**获取 unique_constraint_name*/
	public String getUnique_constraint_name() {
		return unique_constraint_name;
	}
	/**设置 unique_constraint_name*/
	public void setUnique_constraint_name(String unique_constraint_name) {
		this.unique_constraint_name = unique_constraint_name;
	}
	/**获取 match_option*/
	public String getMatch_option() {
		return match_option;
	}
	/**设置 match_option*/
	public void setMatch_option(String match_option) {
		this.match_option = match_option;
	}
	/**获取 update_rule*/
	public String getUpdate_rule() {
		return update_rule;
	}
	/**设置 update_rule*/
	public void setUpdate_rule(String update_rule) {
		this.update_rule = update_rule;
	}
	/**获取 delete_rule*/
	public String getDelete_rule() {
		return delete_rule;
	}
	/**设置 delete_rule*/
	public void setDelete_rule(String delete_rule) {
		this.delete_rule = delete_rule;
	}
	/**获取 table_name*/
	public String getTable_name() {
		return table_name;
	}
	/**设置 table_name*/
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	/**获取 referenced_table_name*/
	public String getReferenced_table_name() {
		return referenced_table_name;
	}
	/**设置 referenced_table_name*/
	public void setReferenced_table_name(String referenced_table_name) {
		this.referenced_table_name = referenced_table_name;
	}
}
