package com.ccloomi.core.util;

/**
 * 类    名：JdbcConstants
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年12月23日-下午12:46:13
 */
public interface JdbcConstants {

    public static final String JTDS              = "jtds";

    public static final String MOCK              = "mock";

    public static final String HSQL              = "hsql";

    public static final String DB2               = "db2";

    public static final String DB2_DRIVER        = "COM.ibm.db2.jdbc.app.DB2Driver";

    public static final String POSTGRESQL        = "postgresql";
    public static final String POSTGRESQL_DRIVER = "org.postgresql.Driver";

    public static final String SYBASE            = "sybase";

    public static final String SQL_SERVER        = "sqlserver";
    public static final String SQL_SERVER_DRIVER = "com.microsoft.jdbc.sqlserver.SQLServerDriver";

    public static final String ORACLE            = "oracle";
    public static final String ORACLE_DRIVER     = "oracle.jdbc.OracleDriver";

    public static final String ALI_ORACLE        = "AliOracle";
    public static final String ALI_ORACLE_DRIVER = "com.alibaba.jdbc.AlibabaDriver";

    public static final String MYSQL             = "mysql";
    public static final String MYSQL_DRIVER      = "com.mysql.jdbc.Driver";

    public static final String MARIADB           = "mariadb";
    public static final String MARIADB_DRIVER    = "org.mariadb.jdbc.Driver";

    public static final String DERBY             = "derby";

    public static final String HBASE             = "hbase";

    public static final String HIVE              = "hive";
    public static final String HIVE_DRIVER       = "org.apache.hive.jdbc.HiveDriver";

    public static final String H2                = "h2";
    public static final String H2_DRIVER         = "org.h2.Driver";

    public static final String DM                = "dm";
    public static final String DM_DRIVER         = "dm.jdbc.driver.DmDriver";
    
    public static final String KINGBASE          = "kingbase";
    public static final String KINGBASE_DRIVER   = "com.kingbase.Driver";
    
    /**阿里云odps*/
    public static final String ODPS              = "odps";
    
    /**Log4JDBC*/
    public static final String LOG4JDBC          = "log4jdbc";
    public static final String LOG4JDBC_DRIVER   = "net.sf.log4jdbc.DriverSpy";
}
