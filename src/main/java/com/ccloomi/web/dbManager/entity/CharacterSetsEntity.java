package com.ccloomi.web.dbManager.entity;

import java.math.BigInteger;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CharacterSetsEntity
 * 类 描 述：字符集
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午8:48:06
 */
@Table(name="INFORMATION_SCHEMA.CHARACTER_SETS")
public class CharacterSetsEntity extends BaseEntity{
	private static final long serialVersionUID = -3983811982475106739L;
	private String character_set_name;
	private String default_collate_name;
	private String description;
	private BigInteger maxlen;
	/**获取 character_set_name*/
	public String getCharacter_set_name() {
		return character_set_name;
	}
	/**设置 character_set_name*/
	public void setCharacter_set_name(String character_set_name) {
		this.character_set_name = character_set_name;
	}
	/**获取 default_collate_name*/
	public String getDefault_collate_name() {
		return default_collate_name;
	}
	/**设置 default_collate_name*/
	public void setDefault_collate_name(String default_collate_name) {
		this.default_collate_name = default_collate_name;
	}
	/**获取 description*/
	public String getDescription() {
		return description;
	}
	/**设置 description*/
	public void setDescription(String description) {
		this.description = description;
	}
	/**获取 maxlen*/
	public BigInteger getMaxlen() {
		return maxlen;
	}
	/**设置 maxlen*/
	public void setMaxlen(BigInteger maxlen) {
		this.maxlen = maxlen;
	}
}
