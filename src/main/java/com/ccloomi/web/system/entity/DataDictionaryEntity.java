package com.ccloomi.web.system.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：DataDictionaryEntity
 * 类 描 述：数据字典实体类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月19日-下午4:25:11
 */
@Entity
@Table(name="sys_data_dictionary")
public class DataDictionaryEntity extends IdEntity{
	private static final long serialVersionUID = 1652018961605533670L;
	private String name;
	private String code;
	private String pid;
	private String rootId;
	private String vl;
	private int deepIndex;
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
	/**获取 pid*/
	public String getPid() {
		return pid;
	}
	/**设置 pid*/
	public void setPid(String pid) {
		this.pid = pid;
	}
	/**获取 rootId*/
	public String getRootId() {
		return rootId;
	}
	/**设置 rootId*/
	public void setRootId(String rootId) {
		this.rootId = rootId;
	}
	/**获取 vl*/
	public String getVl() {
		return vl;
	}
	/**设置 vl*/
	public void setVl(String vl) {
		this.vl = vl;
	}
	/**获取 deepIndex*/
	public int getDeepIndex() {
		return deepIndex;
	}
	/**设置 deepIndex*/
	public void setDeepIndex(int deepIndex) {
		this.deepIndex = deepIndex;
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
