package com.ccloomi.core.component.sql;

import com.ccloomi.core.component.sql.maker.DB2SQLMaker;
import com.ccloomi.core.component.sql.maker.MySQLSQLMaker;
import com.ccloomi.core.component.sql.maker.OracleSQLMaker;

/**
 * 类    名：SQLMakerFactory
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年12月23日-下午12:01:17
 */
public class SQLMakerFactory {
	private static SQLMakerFactory instance;
//	private static String jdbcUrl;
	public synchronized static SQLMakerFactory getInstance(){
//		WebApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		if(instance==null){
			instance=new SQLMakerFactory();
		}
		return instance;
	}
	public SQLMaker createMapker(DBType dbType){
		if(dbType==DBType.mysql){
			return new MySQLSQLMaker();
		}else if(dbType==DBType.oracle){
			return new OracleSQLMaker();
		}else if(dbType==DBType.db2){
			return new DB2SQLMaker();
		}else{
			return new MySQLSQLMaker();
		}
	}
}
