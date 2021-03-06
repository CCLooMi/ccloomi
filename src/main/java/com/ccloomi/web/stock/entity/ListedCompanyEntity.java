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
	private Float IssuePrice;
	/**董事长*/
	private String chairman;
	/**董秘*/
	private String secretaries;
	/**上市日期*/
	private Date listedDate;
	/**实际控制人*/
	private String actualController;
	/**经度*/
	private String longitude;
	/**纬度*/
	private String latitude;
	/**区域编码*/
	private String addressCode;
	/**获取 公司名称*/
	public String getName() {
		return name;
	}
	/**设置 公司名称*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 主营业务*/
	public String getMainBusiness() {
		return mainBusiness;
	}
	/**设置 主营业务*/
	public void setMainBusiness(String mainBusiness) {
		this.mainBusiness = mainBusiness;
	}
	/**获取 注册地址*/
	public String getRegisteredAddress() {
		return registeredAddress;
	}
	/**设置 注册地址*/
	public void setRegisteredAddress(String registeredAddress) {
		this.registeredAddress = registeredAddress;
	}
	/**获取 发行价*/
	public Float getIssuePrice() {
		return IssuePrice;
	}
	/**设置 发行价*/
	public void setIssuePrice(Float issuePrice) {
		IssuePrice = issuePrice;
	}
	/**获取 董事长*/
	public String getChairman() {
		return chairman;
	}
	/**设置 董事长*/
	public void setChairman(String chairman) {
		this.chairman = chairman;
	}
	/**获取 董秘*/
	public String getSecretaries() {
		return secretaries;
	}
	/**设置 董秘*/
	public void setSecretaries(String secretaries) {
		this.secretaries = secretaries;
	}
	/**获取 上市日期*/
	public Date getListedDate() {
		return listedDate;
	}
	/**设置 上市日期*/
	public void setListedDate(Date listedDate) {
		this.listedDate = listedDate;
	}
	/**获取 实际控制人*/
	public String getActualController() {
		return actualController;
	}
	/**设置 实际控制人*/
	public void setActualController(String actualController) {
		this.actualController = actualController;
	}
	/**获取 经度*/
	public String getLongitude() {
		return longitude;
	}
	/**设置 经度*/
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**获取 纬度*/
	public String getLatitude() {
		return latitude;
	}
	/**设置 纬度*/
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**获取 区域编码*/
	public String getAddressCode() {
		return addressCode;
	}
	/**设置 区域编码*/
	public void setAddressCode(String addressCode) {
		this.addressCode = addressCode;
	}
}
