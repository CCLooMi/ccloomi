package com.ccloomi.web.system.service;

import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.TemplateEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TemplateService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月17日-下午5:08:56
 */
public interface TemplateService extends BaseService<TemplateEntity>{

	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年4月17日 - 下午5:43:41
	 * @param map
	 * @return
	 */
	Map<String, Object> findByPage(Map<String, Object> map);
	
}
