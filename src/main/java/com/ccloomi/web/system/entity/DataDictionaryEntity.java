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
	private String code;
	private String K;
	private String V;
	private String pid;
	
	/**获取 code*/
	public String getCode() {
		return code;
	}
	/**设置 code*/
	public void setCode(String code) {
		this.code = code;
	}
	/**获取 k*/
	public String getK() {
		return K;
	}
	/**设置 k*/
	public void setK(String k) {
		K = k;
	}
	/**获取 v*/
	public String getV() {
		return V;
	}
	/**设置 v*/
	public void setV(String v) {
		V = v;
	}
	/**获取 pid*/
	public String getPid() {
		return pid;
	}
	/**设置 pid*/
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}
