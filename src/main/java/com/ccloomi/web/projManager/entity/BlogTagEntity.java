package com.ccloomi.web.projManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogTagEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月16日-下午9:39:01
 */
@Table(name="t_b_tag")
public class BlogTagEntity extends IdEntity{
	private static final long serialVersionUID = 7832973016216516931L;
	/**博客ID*/
	private String idBlog;
	/**标签名称*/
	private String name;
	/**获取 idBlog*/
	public String getIdBlog() {
		return idBlog;
	}
	/**设置 idBlog*/
	public void setIdBlog(String idBlog) {
		this.idBlog = idBlog;
	}
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
}
