package com.ccloomi.web.projManager.entity;

import java.util.Date;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogCommentEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月16日-下午9:37:42
 */
@Table(name="t_b_comment")
public class BlogCommentEntity extends IdEntity{
	private static final long serialVersionUID = 4049806233424503343L;
	/**博客ID*/
	private String idBlog;
	/**用户ID*/
	private String idUser;
	/**评论内容*/
	private String content;
	/**评论日期*/
	private Date datetime;
	/**父评论ID*/
	private String pid;
	/**顶层评论ID*/
	private String idRoot;
	/**获取 idBlog*/
	public String getIdBlog() {
		return idBlog;
	}
	/**设置 idBlog*/
	public void setIdBlog(String idBlog) {
		this.idBlog = idBlog;
	}
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
	/**获取 pid*/
	public String getPid() {
		return pid;
	}
	/**设置 pid*/
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**获取 idRoot*/
	public String getIdRoot() {
		return idRoot;
	}
	/**设置 idRoot*/
	public void setIdRoot(String idRoot) {
		this.idRoot = idRoot;
	}
}
