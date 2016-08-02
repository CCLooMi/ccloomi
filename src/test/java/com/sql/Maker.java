package com.sql;

import java.util.Collection;

import com.sql.entity.BaseEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Maker
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-下午12:59:11
 */
public interface Maker {
	public Maker SELECT(String...columns);
	public Maker SELECT_AS(String column,String alias);
	public Maker UPDATE(BaseEntity entity,String alias);
	public Maker DELETE(BaseEntity entity,String alias);
	public Maker INSERT_INTO(BaseEntity entity,String alias);
	public Maker CREATE_DATABASE(String schema_name);
	public Maker DEFAULT_CHARACTER_SET(String default_character_set);
	public Maker COLLATE(String collate);
	public Maker INTO_COLUMNS(String...columns);
	public Maker INTO_COLUMNS(Collection<? extends Object>columns);
	@SuppressWarnings("unchecked")
	public <value>Maker INTO_VALUES(value...values);
	public Maker FROM(BaseEntity entity,String alias);
	public Maker FROM_SELECT(SQLGod sm,String alias);
	public Maker SELECT_BEFORE_AS(String alias);
	@SuppressWarnings("unchecked")
	public <value>Maker LEFT_JOIN(BaseEntity entity,String alias,String on,value...values);
	@SuppressWarnings("unchecked")
	public <value>Maker RIGHT_JOIN(BaseEntity entity,String alias,String on,value...values);
	@SuppressWarnings("unchecked")
	public <value>Maker WHERE(String str,value...values);
	public Maker WHERE_IN(String str,Collection<? extends Object>values);
	@SuppressWarnings("unchecked")
	public <value>Maker WHERE_IN(String str,value...values);
	@SuppressWarnings("unchecked")
	public <value>Maker AND(String str,value...values);
	@SuppressWarnings("unchecked")
	public <value>Maker OR(String str,value...values);
	@SuppressWarnings("unchecked")
	public <value>Maker SET(String str,value...values);
	public Maker ORDER_BY(String...columns);
	public Maker GROUP_BY(String...columns);
	public String InsertBeforeSQL();
	public String InsertAfterSQL();
	public abstract Maker LIMIT(int page,int pageSize);
}
