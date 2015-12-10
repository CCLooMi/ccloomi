package com.ccloomi.web.dbManager.entity;

import java.math.BigInteger;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CollationsEntity
 * 类 描 述：排序规则
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午8:43:31
 */
@Table(name="INFORMATION_SCHEMA.COLLATIONS")
public class CollationsEntity extends BaseEntity{
	private static final long serialVersionUID = -5036965001569414077L;
	private String collation_name;
	private String character_set_name;
	private long id;
	private String is_default;
	private String is_compiled;
	private long sortlen;
	/**获取 collation_name*/
	public String getCollation_name() {
		return collation_name;
	}
	/**设置 collation_name*/
	public void setCollation_name(String collation_name) {
		this.collation_name = collation_name;
	}
	/**获取 character_set_name*/
	public String getCharacter_set_name() {
		return character_set_name;
	}
	/**设置 character_set_name*/
	public void setCharacter_set_name(String character_set_name) {
		this.character_set_name = character_set_name;
	}
	/**获取 id*/
	public long getId() {
		return id;
	}
	/**设置 id*/
	public void setId(long id) {
		this.id = id;
	}
	/**获取 is_default*/
	public String getIs_default() {
		return is_default;
	}
	/**设置 is_default*/
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	/**获取 is_compiled*/
	public String getIs_compiled() {
		return is_compiled;
	}
	/**设置 is_compiled*/
	public void setIs_compiled(String is_compiled) {
		this.is_compiled = is_compiled;
	}
	/**获取 sortlen*/
	public long getSortlen() {
		return sortlen;
	}
	/**设置 sortlen*/
	public void setSortlen(long sortlen) {
		this.sortlen = sortlen;
	}
}
