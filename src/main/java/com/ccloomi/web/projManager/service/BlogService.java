package com.ccloomi.web.projManager.service;

import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.projManager.entity.BlogEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月20日-下午6:38:07
 */
public interface BlogService extends BaseService<BlogEntity>{

	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年3月20日 - 下午6:50:28
	 * @param map
	 * @return
	 */
	public Map<String, Object> findByPage(Map<String, Object> map);
	
}
