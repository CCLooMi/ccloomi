package com.ccloomi.core.component.sql.maker;

import com.ccloomi.core.component.sql.SQLMaker;

/**
 * 类    名：OracleSQLMaker
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年12月23日-下午12:35:48
 */
public class OracleSQLMaker extends SQLMaker{

	@Override
	public SQLMaker LIMIT(int page, int pageSize) {
		//SELECT * FROM (SELECT t.*,ROWNUM AS rowno FROM emp t WHERE 1=1 AND ROWNUM <= 20) table_alias WHERE table_alias.rowno >= 10;
		//SELECT * FROM ( SELECT tt.*, ROWNUM AS rowno FROM ( SELECT t.*  FROM emp t WHERE 1=1 AND 1=1 ORDER BY create_time DESC,emp_no ) tt WHERE ROWNUM <= 20 ) table_alias WHERE table_alias.rowno >= 10;
		if(this.order_by.size()>0){
			this.sb.insert(0, "SELECT * FROM ( SELECT _.*, ROWNUM AS rowno FROM ( ");
			this.limit=" ) _ WHERE ROWNUM <= ? ) __ WHERE __.rowno >= ?";
			this.values.add(page*pageSize+pageSize);
			this.values.add(page*pageSize);
		}else{
			this.SELECT_AS("ROWNUM", "rowno");
			this.AND("ROWNUM <= ?", page*pageSize+pageSize);
			this.sb.insert(0, "SELECT * FROM ( ");
			this.limit=" ) _ WHERE _.rowno >= ?";
			this.values.add(page*pageSize);
		}
		return this;
	}
}
