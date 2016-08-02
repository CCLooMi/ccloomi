package com.sql.entity;

import java.util.Date;

import com.sql.annotation.MappedSuperclass;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：GenericEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午11:45:13
 */
@MappedSuperclass
public class GenericEntity extends IdEntity{
	private static final long serialVersionUID = -1504991813936442676L;
	private String createBy;
	private Date createDate;
	private String updateBy;
	private Date updateDate;
	/**获取 createBy*/
	public String getCreateBy() {
		return createBy;
	}
	/**设置 createBy*/
	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
	/**获取 createDate*/
	public Date getCreateDate() {
		return createDate;
	}
	/**设置 createDate*/
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**获取 updateBy*/
	public String getUpdateBy() {
		return updateBy;
	}
	/**设置 updateBy*/
	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}
	/**获取 updateDate*/
	public Date getUpdateDate() {
		return updateDate;
	}
	/**设置 updateDate*/
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
}
