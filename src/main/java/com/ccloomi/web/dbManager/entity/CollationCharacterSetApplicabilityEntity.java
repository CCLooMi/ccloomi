package com.ccloomi.web.dbManager.entity;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CollationCharacterSetApplicabilityEntity
 * 类 描 述：排序规则名称和字符集对应表
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午8:34:45
 */
@Table(name="INFORMATION_SCHEMA.COLLATION_CHARACTER_SET_APPLICABILITY")
public class CollationCharacterSetApplicabilityEntity extends BaseEntity{
	private static final long serialVersionUID = 5082383725851607821L;
	/**排序规则名称*/
	private String collation_name;
	/**字符集名称*/
	private String character_set_name;
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
}
