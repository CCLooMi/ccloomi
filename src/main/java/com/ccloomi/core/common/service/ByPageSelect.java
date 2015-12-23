package com.ccloomi.core.common.service;

import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;

/**
 * 类    名：ByPageSelect
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年12月15日-上午10:17:48
 */
public interface ByPageSelect {
	/**
	 * 方法描述：分页查询主体
	 * 作        者：Chenxj
	 * 日        期：2015年12月15日-上午10:18:32
	 * @param sm
	 * @param map
	 */
	public abstract void doSelect(SQLMaker sm,Map<String, Object>map);
}
