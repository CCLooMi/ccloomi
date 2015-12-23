package com.ccloomi.core.component.sql.maker;

import com.ccloomi.core.component.sql.SQLMaker;

/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：MySQLSQLMaker
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月23日-下午12:35:15
 */
public class MySQLSQLMaker extends SQLMaker{
	@Override
	public SQLMaker LIMIT(int page, int pageSize) {
		this.limit=" LIMIT ?,?";
		this.values.add(page*pageSize);
		this.values.add(pageSize);
		return this;
	}
}
