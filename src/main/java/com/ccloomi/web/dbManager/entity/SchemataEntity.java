package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SchemataEntity
 * 类 描 述：数据库实体类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午5:51:36
 */
@Table(name="INFORMATION_SCHEMA.SCHEMATA")
public class SchemataEntity extends BaseEntity{
	private static final long serialVersionUID = 7107330623110964085L;
	private String catalog_name;
	private String schema_name;
	private String default_character_set_name;
	private String default_collation_name;
	private String sql_path;
	/**获取 catalog_name*/
	public String getCatalog_name() {
		return catalog_name;
	}
	/**设置 catalog_name*/
	public void setCatalog_name(String catalog_name) {
		this.catalog_name = catalog_name;
	}
	/**获取 schema_name*/
	public String getSchema_name() {
		return schema_name;
	}
	/**设置 schema_name*/
	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}
	/**获取 default_character_set_name*/
	public String getDefault_character_set_name() {
		return default_character_set_name;
	}
	/**设置 default_character_set_name*/
	public void setDefault_character_set_name(String default_character_set_name) {
		this.default_character_set_name = default_character_set_name;
	}
	/**获取 default_collation_name*/
	public String getDefault_collation_name() {
		return default_collation_name;
	}
	/**设置 default_collation_name*/
	public void setDefault_collation_name(String default_collation_name) {
		this.default_collation_name = default_collation_name;
	}
	/**获取 sql_path*/
	public String getSql_path() {
		return sql_path;
	}
	/**设置 sql_path*/
	public void setSql_path(String sql_path) {
		this.sql_path = sql_path;
	}
}
