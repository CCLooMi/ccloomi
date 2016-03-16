package com.ccloomi.web.projManager.entity;

import java.util.Date;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月16日-下午9:33:34
 */
@Table(name="t_b_blog")
public class BlogEntity extends IdEntity{
	private static final long serialVersionUID = 2421117295044689880L;
	/**用户ID*/
	private String idUser;
	/**博客内容*/
	private String content;
	/**日期*/
	private Date datetime;
	/**博客分类ID*/
	private String idCategory;
	
	/**获取 idUser*/
	public String getIdUser() {
		return idUser;
	}
	/**设置 idUser*/
	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}
	/**获取 content*/
	public String getContent() {
		return content;
	}
	/**设置 content*/
	public void setContent(String content) {
		this.content = content;
	}
	/**获取 datetime*/
	public Date getDatetime() {
		return datetime;
	}
	/**设置 datetime*/
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	/**获取 idCategory*/
	public String getIdCategory() {
		return idCategory;
	}
	/**设置 idCategory*/
	public void setIdCategory(String idCategory) {
		this.idCategory = idCategory;
	}
}
