package com.ccloomi.core.component.sql;

import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.component.sql.maker.DB2SQLMaker;
import com.ccloomi.core.component.sql.maker.MySQLSQLMaker;
import com.ccloomi.core.component.sql.maker.OracleSQLMaker;
import com.ccloomi.core.util.JdbcUtil;

/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SQLMakerFactory
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月23日-下午12:01:17
 */
public final class SQLMakerFactory {
	private final static Logger log=LoggerFactory.getLogger(SQLMakerFactory.class);
	private static SQLMakerFactory instance;
	private static DBType dbType;
	static {
		try{
			ClassLoader ctxClassLoader=Thread.currentThread().getContextClassLoader();
			if(ctxClassLoader!=null){
				//发布到服务器后文件地址
				for(Enumeration<URL>e=ctxClassLoader.getResources("WEB-INF/classes/properties/jdbc.properties");e.hasMoreElements();){
					URL url=e.nextElement();
					Properties property=new Properties();
					InputStream is=null;
					try{
						is=url.openStream();
						property.load(is);
						String jdbcUrl=property.getProperty("jdbc.jdbcUrl");
						dbType=DBType.valueOf(JdbcUtil.getDbType(jdbcUrl, JdbcUtil.getDriverClassName(jdbcUrl)));
						log.debug("load [WEB-INF/classes/properties/jdbc.properties] success");
					}finally{
						if(is!=null){
							is.close();
						}
					}
				}
				//文件本地地址
				for(Enumeration<URL>e=ctxClassLoader.getResources("properties/jdbc.properties");e.hasMoreElements();){
					URL url=e.nextElement();
					Properties property=new Properties();
					InputStream is=null;
					try{
						is=url.openStream();
						property.load(is);
						String jdbcUrl=property.getProperty("jdbc.jdbcUrl");
						dbType=DBType.valueOf(JdbcUtil.getDbType(jdbcUrl, JdbcUtil.getDriverClassName(jdbcUrl)));
						log.debug("load [properties/jdbc.properties] success");
					}finally{
						if(is!=null){
							is.close();
						}
					}
				}
			}
		}catch(Exception e){
			log.error("load jdbc.properties error", e);
		}
	}
	public synchronized static SQLMakerFactory getInstance(){
//		WebApplicationContext ctx=ContextLoader.getCurrentWebApplicationContext();
		if(instance==null){
			instance=new SQLMakerFactory();
		}
		return instance;
	}
	public SQLMaker createMapker(){
		if(dbType==DBType.mysql){
			return new MySQLSQLMaker();
		}else if(dbType==DBType.oracle){
			return new OracleSQLMaker();
		}else if(dbType==DBType.db2){
			return new DB2SQLMaker();
		}
		return new MySQLSQLMaker();
	}
}
