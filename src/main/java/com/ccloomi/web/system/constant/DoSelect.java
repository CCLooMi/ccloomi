package com.ccloomi.web.system.constant;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DoSelect
 * 类 描 述：查询及结果处理接口
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-上午8:21:41
 */
public interface DoSelect {
	/**
	 * 描述：查询
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 上午11:43:46
	 * @param sm
	 */
	public void doSelect(SQLMaker sm);
	/**
	 * 描述：处理结果
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 上午11:43:58
	 * @param lsMap
	 * @return
	 */
	public <T>Map<String, T>convertToMap(List<Map<String,Object>>lsMap);
}
