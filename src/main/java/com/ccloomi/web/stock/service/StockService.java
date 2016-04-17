package com.ccloomi.web.stock.service;

import java.io.File;
import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.stock.entity.StockEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StockService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-上午9:59:44
 */
public interface StockService extends BaseService<StockEntity>{
	/**
	 * 描述：导入股票数据,同时会导入tag信息
	 * 作者：Chenxj
	 * 日期：2016年3月27日 - 下午3:17:22
	 * @param excelFile [id	name	type	area	industry]
	 */
	public void importStockInfoFromFile(File excelFile);

	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年4月16日 - 下午11:32:39
	 * @param map
	 * @return
	 */
	public Map<String, Object> findByPage(Map<String, Object> map);

	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年4月17日 - 下午6:55:59
	 * @param id
	 * @return
	 */
	public boolean syncCompanyInfo(Object idStock);
}
