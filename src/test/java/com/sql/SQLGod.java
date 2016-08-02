package com.sql;

import java.util.List;
import java.util.Map;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SQLGod
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年8月27日-下午10:21:00
 */
public interface SQLGod {
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月27日 - 下午10:22:56
	 * @return
	 */
	public Map<String, List<? extends Object>> sql();
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月27日 - 下午11:25:00
	 * @return
	 */
	public Map<String, List<Object[]>>batchSql();
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年11月6日 - 下午9:49:21
	 * @return
	 */
	public Map<String, List<? extends Object>> countSql();
}
