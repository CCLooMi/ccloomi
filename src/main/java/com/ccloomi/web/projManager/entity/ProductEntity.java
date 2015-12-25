package com.ccloomi.web.projManager.entity;

import java.util.Date;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProductEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月25日-下午10:00:19
 */
@Table(name="t_cc_product")
public class ProductEntity extends IdEntity{
	private static final long serialVersionUID = -3757482568304787110L;
	private String name;
	private String code;
	private String introduction;
	private String productPIC;
	private String testPIC;
	private String releasePIC;
	private String idUser;
	private String accessType;
	private Date createdDate;
	private String createdBy;
	private int deleted;
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 code*/
	public String getCode() {
		return code;
	}
	/**设置 code*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取 introduction*/
	public String getIntroduction() {
		return introduction;
	}
	/**设置 introduction*/
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	/**获取 productPIC*/
	public String getProductPIC() {
		return productPIC;
	}
	/**设置 productPIC*/
	public void setProductPIC(String productPIC) {
		this.productPIC = productPIC;
	}
	/**获取 testPIC*/
	public String getTestPIC() {
		return testPIC;
	}
	/**设置 testPIC*/
	public void setTestPIC(String testPIC) {
		this.testPIC = testPIC;
	}
	/**获取 releasePIC*/
	public String getReleasePIC() {
		return releasePIC;
	}
	/**设置 releasePIC*/
	public void setReleasePIC(String releasePIC) {
		this.releasePIC = releasePIC;
	}
	/**获取 idUser*/
	public String getIdUser() {
		return idUser;
	}
	/**设置 idUser*/
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**获取 accessType*/
	public String getAccessType() {
		return accessType;
	}
	/**设置 accessType*/
	public void setAccessType(String accessType) {
		this.accessType = accessType;
	}
	/**获取 createdDate*/
	public Date getCreatedDate() {
		return createdDate;
	}
	/**设置 createdDate*/
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	/**获取 createdBy*/
	public String getCreatedBy() {
		return createdBy;
	}
	/**设置 createdBy*/
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**获取 deleted*/
	public int getDeleted() {
		return deleted;
	}
	/**设置 deleted*/
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
}
