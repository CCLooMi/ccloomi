package com.ccloomi.web.stock.entity;

import java.util.Date;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ListedCompanyEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-下午6:16:12
 */
@Table(name="t_s_listed_company")
public class ListedCompanyEntity extends IdEntity{
	private static final long serialVersionUID = 7627266077069542386L;
	/**公司名称*/
	private String name;
	/**主营业务*/
	private String mainBusiness;
	/**注册地址*/
	private String registeredAddress;
	/**发行价*/
	private Integer IssuePrice;
	/**董事长*/
	private String chairman;
	/**董秘*/
	private String secretaries;
	/**上市日期*/
	private Date listedDate;
	/**实际控制人*/
	private String actualController;
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 mainBusiness*/
	public String getMainBusiness() {
		return mainBusiness;
	}
	/**设置 mainBusiness*/
	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}
	/**获取 registeredAddress*/
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	/**设置 registeredAddress*/
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	/**获取 issuePrice*/
	public Integer getIssuePrice() {
		return IssuePrice;
	}
	/**设置 issuePrice*/
	public void setIssuePrice(Integer issuePrice) {
		IssuePrice = issuePrice;
	}
	/**获取 chairman*/
	public String getChairman() {
		return chairman;
	}
	/**设置 chairman*/
	public void setChairman(String chairman) {
		this.chairman = chairman;
	}
	/**获取 secretaries*/
	public String getSecretaries() {
		return secretaries;
	}
	/**设置 secretaries*/
	public void setSecretaries(String secretaries) {
		this.secretaries = secretaries;
	}
	/**获取 listedDate*/
	public Date getListedDate() {
		return listedDate;
	}
	/**设置 listedDate*/
	public void setListedDate(Date listedDate) {
		this.listedDate = listedDate;
	}
	/**获取 actualController*/
	public String getActualController() {
		return actualController;
	}
	/**设置 actualController*/
	public void setActualController(String actualController) {
		this.actualController = actualController;
	}
}
