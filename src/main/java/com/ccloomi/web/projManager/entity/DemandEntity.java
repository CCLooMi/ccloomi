package com.ccloomi.web.projManager.entity;

import java.util.Date;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DemandEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月9日-上午10:30:22
 */
public class DemandEntity extends IdEntity{
	private static final long serialVersionUID = -5748869312146365007L;
	/**需求标题*/
	private String title;
	
	/**所属产品ID*/
	private String idProduct;

	/**所属产品模块ID*/
	private String idModule;

	/**所属计划ID*/
	private String idPlan;

	/**需求来源*/
	private String comeFrom;

	/**需求状态0草稿1变更2激活3关闭（必需为激活状态才能与项目进行关联）*/
	private Integer status;

	/**需求阶段0未开始1已计划2已立项3开发中4开发完毕5测试中6测试完毕7已验收8已发布*/
	private Integer stage;

	/**优先级*/
	private Integer priority;

	/**预计工时*/
	private Integer estimateHours;

	/**创建人姓名*/
	private String createdBy;

	/**创建日期*/
	private Date createdDate;

	/**被指派人姓名*/
	private String assignedTo;

	/**指派日期*/
	private Date assignedDate;

	/**最后修改人姓名*/
	private String lastEditedBy;

	/**最后修改日期*/
	private Date lastEditedDate;

	/**评审人姓名*/
	private String reviewedBy;

	/**评审日期*/
	private Date reviewedDate;

	/**关闭人姓名*/
	private String closedBy;

	/**关闭日期*/
	private Date closedDate;

	/**是否删除0否1删除*/
	private Integer deleted;

	/**获取 title*/
	public String getTitle() {
		return title;
	}

	/**设置 title*/
	public void setTitle(String title) {
		this.title = title;
	}

	/**获取 idProduct*/
	public String getIdProduct() {
		return idProduct;
	}

	/**设置 idProduct*/
	public void setIdProduct(String idProduct) {
		this.idProduct = idProduct;
	}

	/**获取 idModule*/
	public String getIdModule() {
		return idModule;
	}

	/**设置 idModule*/
	public void setIdModule(String idModule) {
		this.idModule = idModule;
	}

	/**获取 idPlan*/
	public String getIdPlan() {
		return idPlan;
	}

	/**设置 idPlan*/
	public void setIdPlan(String idPlan) {
		this.idPlan = idPlan;
	}

	/**获取 comeFrom*/
	public String getComeFrom() {
		return comeFrom;
	}

	/**设置 comeFrom*/
	public void setComeFrom(String comeFrom) {
		this.comeFrom = comeFrom;
	}

	/**获取 status*/
	public Integer getStatus() {
		return status;
	}

	/**设置 status*/
	public void setStatus(Integer status) {
		this.status = status;
	}

	/**获取 stage*/
	public Integer getStage() {
		return stage;
	}

	/**设置 stage*/
	public void setStage(Integer stage) {
		this.stage = stage;
	}

	/**获取 priority*/
	public Integer getPriority() {
		return priority;
	}

	/**设置 priority*/
	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	/**获取 estimateHours*/
	public Integer getEstimateHours() {
		return estimateHours;
	}

	/**设置 estimateHours*/
	public void setEstimateHours(Integer estimateHours) {
		this.estimateHours = estimateHours;
	}

	/**获取 createdBy*/
	public String getCreatedBy() {
		return createdBy;
	}

	/**设置 createdBy*/
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**获取 createdDate*/
	public Date getCreatedDate() {
		return createdDate;
	}

	/**设置 createdDate*/
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**获取 assignedTo*/
	public String getAssignedTo() {
		return assignedTo;
	}

	/**设置 assignedTo*/
	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	/**获取 assignedDate*/
	public Date getAssignedDate() {
		return assignedDate;
	}

	/**设置 assignedDate*/
	public void setAssignedDate(Date assignedDate) {
		this.assignedDate = assignedDate;
	}

	/**获取 lastEditedBy*/
	public String getLastEditedBy() {
		return lastEditedBy;
	}

	/**设置 lastEditedBy*/
	public void setLastEditedBy(String lastEditedBy) {
		this.lastEditedBy = lastEditedBy;
	}

	/**获取 lastEditedDate*/
	public Date getLastEditedDate() {
		return lastEditedDate;
	}

	/**设置 lastEditedDate*/
	public void setLastEditedDate(Date lastEditedDate) {
		this.lastEditedDate = lastEditedDate;
	}

	/**获取 reviewedBy*/
	public String getReviewedBy() {
		return reviewedBy;
	}

	/**设置 reviewedBy*/
	public void setReviewedBy(String reviewedBy) {
		this.reviewedBy = reviewedBy;
	}

	/**获取 reviewedDate*/
	public Date getReviewedDate() {
		return reviewedDate;
	}

	/**设置 reviewedDate*/
	public void setReviewedDate(Date reviewedDate) {
		this.reviewedDate = reviewedDate;
	}

	/**获取 closedBy*/
	public String getClosedBy() {
		return closedBy;
	}

	/**设置 closedBy*/
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}

	/**获取 closedDate*/
	public Date getClosedDate() {
		return closedDate;
	}

	/**设置 closedDate*/
	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	/**获取 deleted*/
	public Integer getDeleted() {
		return deleted;
	}

	/**设置 deleted*/
	public void setDeleted(Integer deleted) {
		this.deleted = deleted;
	}
}
