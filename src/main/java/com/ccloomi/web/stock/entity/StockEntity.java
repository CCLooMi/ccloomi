package com.ccloomi.web.stock.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StockEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-上午9:48:01
 */
@Table(name="t_s_stock")
public class StockEntity extends IdEntity{
	private static final long serialVersionUID = 7937608458098181848L;
	/**股票名称*/
	private String name;
	/**股票类型*/
	private String type;
	/**股票地区*/
	private String area;
	/**行业*/
	private String industry;
	
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 type*/
	public String getType() {
		return type;
	}
	/**设置 type*/
	public void setType(String type) {
		this.type = type;
	}
	/**获取 area*/
	public String getArea() {
		return area;
	}
	/**设置 area*/
	public void setArea(String area) {
		this.area = area;
	}
	/**获取 industry*/
	public String getIndustry() {
		return industry;
	}
	/**设置 industry*/
	public void setIndustry(String industry) {
		this.industry = industry;
	}
}
