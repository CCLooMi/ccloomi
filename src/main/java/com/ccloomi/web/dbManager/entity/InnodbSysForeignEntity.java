package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;
/**
 * 类    名：InnodbSysForeignEntity
 * 类描述：表表（外键）对应关系
 * 作    者：Chenxj
 * 日    期：2015年11月23日-上午10:52:07
 */
@Table(name="INFORMATION_SCHEMA.INNODB_SYS_FOREIGN")
public class InnodbSysForeignEntity extends BaseEntity{
	private static final long serialVersionUID = -4824111214180526957L;
	private String id;
	private String for_name;
	private String ref_name;
	private int n_cols;
	private int type;
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
	 * 获取：for_name
	 */
	public String getFor_name() {
		return for_name;
	}
	/**
	 * 设置：for_name
	 */
	public void setFor_name(String for_name) {
		this.for_name = for_name;
	}
	/**
	 * 获取：ref_name
	 */
	public String getRef_name() {
		return ref_name;
	}
	/**
	 * 设置：ref_name
	 */
	public void setRef_name(String ref_name) {
		this.ref_name = ref_name;
	}
	/**
	 * 获取：n_cols
	 */
	public int getN_cols() {
		return n_cols;
	}
	/**
	 * 设置：n_cols
	 */
	public void setN_cols(int n_cols) {
		this.n_cols = n_cols;
	}
	/**
	 * 获取：type
	 */
	public int getType() {
		return type;
	}
	/**
	 * 设置：type
	 */
	public void setType(int type) {
		this.type = type;
	}
}
