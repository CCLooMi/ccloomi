package com.ccloomi.web.dbManager.entity;

import java.math.BigInteger;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ColumnsEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午3:44:45
 */
@Table(name="INFORMATION_SCHEMA.COLUMNS")
public class ColumnsEntity extends BaseEntity{
	private static final long serialVersionUID = 3292505625069881560L;
	private String table_catalog;
	private String table_schema;
	private String table_name;
	private String column_name;
	private BigInteger ordinal_position;
	private String column_default;
	private String is_nullable;
	private String data_type;
	private BigInteger character_maximum_length;
	private BigInteger character_octet_length;
	private BigInteger numeric_precision;
	private BigInteger numeric_scale;
	private BigInteger datetime_precision;
	private String character_set_name;
	private String collation_name;
	private String column_type;
	private String column_key;
	private String extra;
	private String privileges;
	private String column_comment;
	/**获取 table_catalog*/
	public String getTable_catalog() {
		return table_catalog;
	}
	/**设置 table_catalog*/
	public void setTable_catalog(String table_catalog) {
		this.table_catalog = table_catalog;
	}
	/**获取 table_schema*/
	public String getTable_schema() {
		return table_schema;
	}
	/**设置 table_schema*/
	public void setTable_schema(String table_schema) {
		this.table_schema = table_schema;
	}
	/**获取 table_name*/
	public String getTable_name() {
		return table_name;
	}
	/**设置 table_name*/
	public void setTable_name(String table_name) {
		this.table_name = table_name;
	}
	/**获取 column_name*/
	public String getColumn_name() {
		return column_name;
	}
	/**设置 column_name*/
	public void setColumn_name(String column_name) {
		this.column_name = column_name;
	}
	/**获取 ordinal_position*/
	public BigInteger getOrdinal_position() {
		return ordinal_position;
	}
	/**设置 ordinal_position*/
	public void setOrdinal_position(BigInteger ordinal_position) {
		this.ordinal_position = ordinal_position;
	}
	/**获取 column_default*/
	public String getColumn_default() {
		return column_default;
	}
	/**设置 column_default*/
	public void setColumn_default(String column_default) {
		this.column_default = column_default;
	}
	/**获取 is_nullable*/
	public String getIs_nullable() {
		return is_nullable;
	}
	/**设置 is_nullable*/
	public void setIs_nullable(String is_nullable) {
		this.is_nullable = is_nullable;
	}
	/**获取 data_type*/
	public String getData_type() {
		return data_type;
	}
	/**设置 data_type*/
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	/**获取 character_maximum_length*/
	public BigInteger getCharacter_maximum_length() {
		return character_maximum_length;
	}
	/**设置 character_maximum_length*/
	public void setCharacter_maximum_length(BigInteger character_maximum_length) {
		this.character_maximum_length = character_maximum_length;
	}
	/**获取 character_octet_length*/
	public BigInteger getCharacter_octet_length() {
		return character_octet_length;
	}
	/**设置 character_octet_length*/
	public void setCharacter_octet_length(BigInteger character_octet_length) {
		this.character_octet_length = character_octet_length;
	}
	/**获取 numeric_precision*/
	public BigInteger getNumeric_precision() {
		return numeric_precision;
	}
	/**设置 numeric_precision*/
	public void setNumeric_precision(BigInteger numeric_precision) {
		this.numeric_precision = numeric_precision;
	}
	/**获取 numeric_scale*/
	public BigInteger getNumeric_scale() {
		return numeric_scale;
	}
	/**设置 numeric_scale*/
	public void setNumeric_scale(BigInteger numeric_scale) {
		this.numeric_scale = numeric_scale;
	}
	/**获取 datetime_precision*/
	public BigInteger getDatetime_precision() {
		return datetime_precision;
	}
	/**设置 datetime_precision*/
	public void setDatetime_precision(BigInteger datetime_precision) {
		this.datetime_precision = datetime_precision;
	}
	/**获取 character_set_name*/
	public String getCharacter_set_name() {
		return character_set_name;
	}
	/**设置 character_set_name*/
	public void setCharacter_set_name(String character_set_name) {
		this.character_set_name = character_set_name;
	}
	/**获取 collation_name*/
	public String getCollation_name() {
		return collation_name;
	}
	/**设置 collation_name*/
	public void setCollation_name(String collation_name) {
		this.collation_name = collation_name;
	}
	/**获取 column_type*/
	public String getColumn_type() {
		return column_type;
	}
	/**设置 column_type*/
	public void setColumn_type(String column_type) {
		this.column_type = column_type;
	}
	/**获取 column_key*/
	public String getColumn_key() {
		return column_key;
	}
	/**设置 column_key*/
	public void setColumn_key(String column_key) {
		this.column_key = column_key;
	}
	/**获取 extra*/
	public String getExtra() {
		return extra;
	}
	/**设置 extra*/
	public void setExtra(String extra) {
		this.extra = extra;
	}
	/**获取 privileges*/
	public String getPrivileges() {
		return privileges;
	}
	/**设置 privileges*/
	public void setPrivileges(String privileges) {
		this.privileges = privileges;
	}
	/**获取 column_comment*/
	public String getColumn_comment() {
		return column_comment;
	}
	/**设置 column_comment*/
	public void setColumn_comment(String column_comment) {
		this.column_comment = column_comment;
	}
}
