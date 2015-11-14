package com.ccloomi.web.system.service;

import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.IconEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：IconService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月14日-下午8:16:43
 */
public interface IconService extends BaseService<IconEntity>{

	/**描述：
	 * 作者：Chenxj
	 * 日期：2015年11月14日 - 下午8:19:22
	 * @param map
	 * @return
	 */
	Map<String, Object> findIconsByPage(Map<String, Object> map);
	
}
