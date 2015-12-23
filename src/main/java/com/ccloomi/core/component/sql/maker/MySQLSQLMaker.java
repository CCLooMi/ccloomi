package com.ccloomi.core.component.sql.maker;

import com.ccloomi.core.component.sql.SQLMaker;

/**
 * 类    名：MySQLSQLMaker
 * 类描述：
 * 作    者：Chenxj
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
