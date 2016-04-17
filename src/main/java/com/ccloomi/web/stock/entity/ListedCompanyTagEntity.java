package com.ccloomi.web.stock.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ListedCompanyTagEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-下午6:16:38
 */
@Table(name="t_s_listed_company_tag")
public class ListedCompanyTagEntity extends IdEntity{
	private static final long serialVersionUID = -6836414428712190591L;
	/**上市公司ID*/
	private String idListedCompany;
	/**标签ID*/
	private String idTag;
	/**获取 idListedCompany*/
	public String getIdListedCompany() {
		return idListedCompany;
	}
	/**设置 idListedCompany*/
	public void setIdListedCompany(String idListedCompany) {
		this.idListedCompany = idListedCompany;
	}
	/**获取 idTag*/
	public String getIdTag() {
		return idTag;
	}
	/**设置 idTag*/
	public void setIdTag(String idTag) {
		this.idTag = idTag;
	}
}
