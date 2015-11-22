package com.ccloomi.web.dbManager.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.BaseEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：TablesEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午2:55:42
 */
@Table(name="INFORMATION_SCHEMA.TABLES")
public class TablesEntity extends BaseEntity{
	private static final long serialVersionUID = -9068100513586606961L;
	private String table_catalog;
	private String table_schema;
	private String table_name;
	private String table_type;
	private String engine;
	private BigInteger version;
	private String row_format;
	private BigInteger table_rows;
	private BigInteger avg_row_length;
	private BigInteger data_length;
	private BigInteger max_data_length;
	private BigInteger index_length;
	private BigInteger data_free;
	private BigInteger auto_increment;
	private Date create_time;
	private Date update_time;
	private Date check_time;
	private String table_collation;
	private BigInteger checksum;
	private String create_options;
	private String table_comment;
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
	/**获取 table_type*/
	public String getTable_type() {
		return table_type;
	}
	/**设置 table_type*/
	public void setTable_type(String table_type) {
		this.table_type = table_type;
	}
	/**获取 engine*/
	public String getEngine() {
		return engine;
	}
	/**设置 engine*/
	public void setEngine(String engine) {
		this.engine = engine;
	}
	/**获取 version*/
	public BigInteger getVersion() {
		return version;
	}
	/**设置 version*/
	public void setVersion(BigInteger version) {
		this.version = version;
	}
	/**获取 row_format*/
	public String getRow_format() {
		return row_format;
	}
	/**设置 row_format*/
	public void setRow_format(String row_format) {
		this.row_format = row_format;
	}
	/**获取 table_rows*/
	public BigInteger getTable_rows() {
		return table_rows;
	}
	/**设置 table_rows*/
	public void setTable_rows(BigInteger table_rows) {
		this.table_rows = table_rows;
	}
	/**获取 avg_row_length*/
	public BigInteger getAvg_row_length() {
		return avg_row_length;
	}
	/**设置 avg_row_length*/
	public void setAvg_row_length(BigInteger avg_row_length) {
		this.avg_row_length = avg_row_length;
	}
	/**获取 data_length*/
	public BigInteger getData_length() {
		return data_length;
	}
	/**设置 data_length*/
	public void setData_length(BigInteger data_length) {
		this.data_length = data_length;
	}
	/**获取 max_data_length*/
	public BigInteger getMax_data_length() {
		return max_data_length;
	}
	/**设置 max_data_length*/
	public void setMax_data_length(BigInteger max_data_length) {
		this.max_data_length = max_data_length;
	}
	/**获取 index_length*/
	public BigInteger getIndex_length() {
		return index_length;
	}
	/**设置 index_length*/
	public void setIndex_length(BigInteger index_length) {
		this.index_length = index_length;
	}
	/**获取 data_free*/
	public BigInteger getData_free() {
		return data_free;
	}
	/**设置 data_free*/
	public void setData_free(BigInteger data_free) {
		this.data_free = data_free;
	}
	/**获取 auto_increment*/
	public BigInteger getAuto_increment() {
		return auto_increment;
	}
	/**设置 auto_increment*/
	public void setAuto_increment(BigInteger auto_increment) {
		this.auto_increment = auto_increment;
	}
	/**获取 create_time*/
	public Date getCreate_time() {
		return create_time;
	}
	/**设置 create_time*/
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	/**获取 update_time*/
	public Date getUpdate_time() {
		return update_time;
	}
	/**设置 update_time*/
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	/**获取 check_time*/
	public Date getCheck_time() {
		return check_time;
	}
	/**设置 check_time*/
	public void setCheck_time(Date check_time) {
		this.check_time = check_time;
	}
	/**获取 table_collation*/
	public String getTable_collation() {
		return table_collation;
	}
	/**设置 table_collation*/
	public void setTable_collation(String table_collation) {
		this.table_collation = table_collation;
	}
	/**获取 checksum*/
	public BigInteger getChecksum() {
		return checksum;
	}
	/**设置 checksum*/
	public void setChecksum(BigInteger checksum) {
		this.checksum = checksum;
	}
	/**获取 create_options*/
	public String getCreate_options() {
		return create_options;
	}
	/**设置 create_options*/
	public void setCreate_options(String create_options) {
		this.create_options = create_options;
	}
	/**获取 table_comment*/
	public String getTable_comment() {
		return table_comment;
	}
	/**设置 table_comment*/
	public void setTable_comment(String table_comment) {
		this.table_comment = table_comment;
	}
}
