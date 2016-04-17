package com.ccloomi.web.system.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TemplateEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月17日-下午4:53:59
 */
@Table(name="sys_template")
public class TemplateEntity extends IdEntity{
	private static final long serialVersionUID = 3435003584906392033L;
	/**模板名称*/
	private String name;
	/**模板编码*/
	private String code;
	/**模板内容*/
	private String content;
	/**模板说明*/
	private String dsc;
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
	/**获取 content*/
	public String getContent() {
		return content;
	}
	/**设置 content*/
	public void setContent(String content) {
		this.content = content;
	}
	/**获取 dsc*/
	public String getDsc() {
		return dsc;
	}
	/**设置 dsc*/
	public void setDsc(String dsc) {
		this.dsc = dsc;
	}
}
