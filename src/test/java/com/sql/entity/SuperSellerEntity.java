package com.sql.entity;

import com.sql.annotation.ExtendType;
import com.sql.annotation.Table;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SuperSellerEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午9:16:01
 */
@Table(name="t_super_seller")
@ExtendType(0)
public class SuperSellerEntity extends SellerEntity{
	private static final long serialVersionUID = -561886474379976237L;
	private String fax;
	private String tel;
	/**获取 fax*/
	public String getFax() {
		return fax;
	}
	/**设置 fax*/
	public void setFax(String fax) {
		this.fax = fax;
	}
	/**获取 tel*/
	public String getTel() {
		return tel;
	}
	/**设置 tel*/
	public void setTel(String tel) {
		this.tel = tel;
	}
}
