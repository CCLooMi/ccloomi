package com.ccloomi.web.dbManager.entity.enums;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ConstraintType
 * 类 描 述：约束类型
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午5:41:52
 */
public enum ConstraintType {
	PRIMARY_KEY("PRIMARY KEY"),FOREIGN_KEY("FOREIGN KEY");
	private String constraintType;
	ConstraintType(String constraintType){
		this.constraintType=constraintType;
	}
	/**获取 constraintType*/
	public String getConstraintType() {
		return constraintType;
	}
	/**设置 constraintType*/
	public void setConstraintType(String constraintType) {
		this.constraintType = constraintType;
	}
}
