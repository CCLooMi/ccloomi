package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.web.dbManager.entity.enums.ConstraintType;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：TableConstraintsEntity
 * 类 描 述：表约束
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午5:31:14
 */
@Table(name="INFORMATION_SCHEMA.TABLE_CONSTRAINTS")
public class TableConstraintsEntity extends BaseEntity{
	private static final long serialVersionUID = 7933970314816428016L;
	private String constraint_catalog;
	private String constraint_schema;
	private String constraint_name;
	private String table_schema;
	private String table_name;
	private ConstraintType constraint_type;
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
	/**获取 table_schema*/
	public String getTable_schema() {
		return table_schema;
	}
	/**设置 table_schema*/
	public void setTable_schema(String table_schema) {
		this.table_schema = table_schema;
	}
	/**获取 table_name*/
	public String getTable_name() {
		return table_name;
	}
	/**设置 table_name*/
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	/**获取 约束类型::FOREIGN KEY,PRIMARY KEY*/
	public ConstraintType getConstraint_type() {
		return constraint_type;
	}
	/**设置 约束类型::FOREIGN KEY,PRIMARY KEY*/
	public void setConstraint_type(ConstraintType constraint_type) {
		this.constraint_type = constraint_type;
	}
}
