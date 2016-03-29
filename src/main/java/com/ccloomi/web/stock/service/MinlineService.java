package com.ccloomi.web.stock.service;

import java.io.File;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.stock.entity.MinlineEntity;

/**
 * 类名：MinlineService
 * 描述：
 * 作者： Chenxj
 * 日期：2016年3月29日 - 下午1:12:40
 */
public interface MinlineService extends BaseService<MinlineEntity>{
	/**
	 * 方法描述：通过文件导入分钟线数据
	 * 作者：Chenxj
	 * 日期：2016年3月29日 - 下午1:15:43
	 * @param minlinFile
	 */
	public void importMinlineInfoFromMinlineFile(File minlineFile);
	/**
	 * 方法描述：导入文件夹下所有分钟线数据
	 * 作者：Chenxj
	 * 日期：2016年3月29日 - 下午1:42:52
	 * @param minlineDir
	 */
	public void importMinlineInfoFromMinlineDir(File minlineDir);
}
