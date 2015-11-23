package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**
 * 类    名：InnodbSysForeignColsEntity
 * 类描述：表字段（外键）对应关系
 * 作    者：Chenxj
 * 日    期：2015年11月23日-上午11:00:00
 */
@Table(name="INFORMATION_SCHEMA.INNODB_SYS_FOREIGN_COLS")
public class InnodbSysForeignColsEntity extends BaseEntity{
	private static final long serialVersionUID = 1671609357716005334L;
	private String id;
	private String for_col_name;
	private String ref_col_name;
	private int pos;
	/**
	 * 获取：id
	 */
	public String getId() {
		return id;
	}
	/**
	 * 设置：id
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * 获取：for_col_name
	 */
	public String getFor_col_name() {
		return for_col_name;
	}
	/**
	 * 设置：for_col_name
	 */
	public void setFor_col_name(String for_col_name) {
		this.for_col_name = for_col_name;
	}
	/**
	 * 获取：ref_col_name
	 */
	public String getRef_col_name() {
		return ref_col_name;
	}
	/**
	 * 设置：ref_col_name
	 */
	public void setRef_col_name(String ref_col_name) {
		this.ref_col_name = ref_col_name;
	}
	/**
	 * 获取：pos
	 */
	public int getPos() {
		return pos;
	}
	/**
	 * 设置：pos
	 */
	public void setPos(int pos) {
		this.pos = pos;
	}
}
