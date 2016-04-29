package com.ccloomi.web.stock.service;

import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.stock.entity.ListedCompanyEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ListedCompanyService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-下午9:03:03
 */
public interface ListedCompanyService extends BaseService<ListedCompanyEntity>{

	/**描述：获取高德云图模版数据
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 下午8:55:05
	 * @return
	 */
	public List<Map<String, Object>> getMapExcelTemplateData();
	/**
	 * 描述：获取谷歌地球KML数据
	 * 作者：Chenxj
	 * 日期：2016年4月29日 - 下午10:51:15
	 * @return
	 */
	public List<Map<String, Object>> getMapKMLTemplateData();
	
}
