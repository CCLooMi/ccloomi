package com.ccloomi.web.dbManager.entity.enums;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：PrivilegeType
 * 类 描 述：用户权限枚举
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午9:39:50
 */
public enum PrivilegeType {
	SELECT("SELECT"),
	INSERT("INSERT"),
	UPDATE("UPDATE"),
	DELETE("DELETE"),
	CREATE("CREATE"),
	DROP("DROP"),
	RELOAD("RELOAD"),
	SHUTDOWN("SHUTDOWN"),
	PROCESS("PROCESS"),
	FILE("FILE"),
	REFERENCES("REFERENCES"),
	INDEX("INDEX"),
	ALTER("ALTER"),
	SHOW_DATABASES("SHOW DATABASES"),
	SUPER("SUPER"),
	CREATE_TEMPORARY_TABLES("CREATE TEMPORARY TABLES"),
	LOCK_TABLES("LOCK TABLES"),
	EXECUTE("EXECUTE"),
	REPLICATION_SLAVE("REPLICATION SLAVE"),
	REPLICATION_CLIENT("REPLICATION CLIENT"),
	CREATE_VIEW("CREATE VIEW"),
	SHOW_VIEW("SHOW VIEW"),
	CREATE_ROUTINE("CREATE ROUTINE"),
	ALTER_ROUTINE("ALTER ROUTINE"),
	CREATE_USER("CREATE USER"),
	EVENT("EVENT"),
	TRIGGER("TRIGGER"),
	CREATE_TABLESPACE("CREATE TABLESPACE");
	private String privilegeType;
	PrivilegeType(String privilegeType) {
		this.privilegeType=privilegeType;
	}
	/**获取 privilegeType*/
	public String getPrivilegeType() {
		return privilegeType;
	}
	/**设置 privilegeType*/
	public void setPrivilegeType(String privilegeType) {
		this.privilegeType = privilegeType;
	}
}
