package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：EnginesEntity
 * 类 描 述：数据库引擎
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午8:28:31
 */
@Table(name="INFORMATION_SCHEMA.ENGINES")
public class EnginesEntity extends BaseEntity{
	private static final long serialVersionUID = -5087765167356119801L;
	private String engine;
	private String support;
	private String comment;
	private String transactions;
	private String xa;
	private String savepoints;
	/**获取 engine*/
	public String getEngine() {
		return engine;
	}
	/**设置 engine*/
	public void setEngine(String engine) {
		this.engine = engine;
	}
	/**获取 support*/
	public String getSupport() {
		return support;
	}
	/**设置 support*/
	public void setSupport(String support) {
		this.support = support;
	}
	/**获取 comment*/
	public String getComment() {
		return comment;
	}
	/**设置 comment*/
	public void setComment(String comment) {
		this.comment = comment;
	}
	/**获取 transactions*/
	public String getTransactions() {
		return transactions;
	}
	/**设置 transactions*/
	public void setTransactions(String transactions) {
		this.transactions = transactions;
	}
	/**获取 xa*/
	public String getXa() {
		return xa;
	}
	/**设置 xa*/
	public void setXa(String xa) {
		this.xa = xa;
	}
	/**获取 savepoints*/
	public String getSavepoints() {
		return savepoints;
	}
	/**设置 savepoints*/
	public void setSavepoints(String savepoints) {
		this.savepoints = savepoints;
	}
}
