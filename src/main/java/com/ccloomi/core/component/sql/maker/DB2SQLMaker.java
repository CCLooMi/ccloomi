package com.ccloomi.core.component.sql.maker;

import com.ccloomi.core.component.sql.SQLMaker;

/**
 * 类    名：DB2SQLMaker
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年12月23日-下午12:36:16
 */
public class DB2SQLMaker extends SQLMaker{

	@Override
	public SQLMaker LIMIT(int page, int pageSize) {
		this.limit=" BETWEEN ? AND ?";
		this.values.add(page*pageSize);
		this.values.add(page*pageSize+pageSize);
		return this;
	}
	
}
