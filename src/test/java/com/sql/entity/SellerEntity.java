package com.sql.entity;

import com.sql.annotation.ExtendType;
import com.sql.annotation.Table;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SellerEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午9:09:42
 */
@Table(name="t_seller")
@ExtendType(1)
public class SellerEntity extends UserEntity{
	private static final long serialVersionUID = 2392687282974178210L;
	private String address;
	private String website;
	@ExtendType
	private int sellerType;
	/**获取 address*/
	public String getAddress() {
		return address;
	}
	/**设置 address*/
	public void setAddress(String address) {
		this.address = address;
	}
	/**获取 website*/
	public String getWebsite() {
		return website;
	}
	/**设置 website*/
	public void setWebsite(String website) {
		this.website = website;
	}
	/**获取 sellerType*/
	public int getSellerType() {
		return sellerType;
	}
	/**设置 sellerType*/
	public void setSellerType(int sellerType) {
		this.sellerType = sellerType;
	}
}
