package com.ccloomi.web.projManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DemandDetailEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月24日-下午12:52:01
 */
@Table(name="t_cc_demand_detail")
public class DemandDetailEntity extends IdEntity{
	private static final long serialVersionUID = 9089680615164017324L;
	/**需求标题*/
	private String title;
	/**需求详情*/
	private String detail;
	/**验收标准*/
	private String verify;
	/**获取 title*/
	public String getTitle() {
		return title;
	}
	/**设置 title*/
	public void setTitle(String title) {
		this.title = title;
	}
	/**获取 detail*/
	public String getDetail() {
		return detail;
	}
	/**设置 detail*/
	public void setDetail(String detail) {
		this.detail = detail;
	}
	/**获取 verify*/
	public String getVerify() {
		return verify;
	}
	/**设置 verify*/
	public void setVerify(String verify) {
		this.verify = verify;
	}
}
