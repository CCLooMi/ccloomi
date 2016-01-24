package com.ccloomi.web.projManager.service;

import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.projManager.entity.DemandEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DemandService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月10日-下午10:08:34
 */
public interface DemandService extends BaseService<DemandEntity>{
	public Map<String, Object>findByPage(Map<String, Object>map);

	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年1月24日 - 下午2:11:15
	 * @param map
	 * @return
	 */
	public Object add(Map<String, Object> map);

	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年1月24日 - 下午9:26:45
	 * @param map
	 * @return
	 */
	public boolean remove(Map<String, Object> map);
}
