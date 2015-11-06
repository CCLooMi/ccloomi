package com.ccloomi.web.system.service;

import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.system.entity.ViewEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ViewService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午10:58:19
 */
public interface ViewService extends BaseService<ViewEntity>{
	public Map<String, Object> findViewsByPage(Map<String, Object>map);
}
