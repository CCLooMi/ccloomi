package com.ccloomi.web.stock.service;

import java.io.File;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.stock.entity.LdayEntity;

/**
 * 类名：LdayService
 * 描述：
 * 作者： Chenxj
 * 日期：2016年3月29日 - 下午1:12:09
 */
public interface LdayService extends BaseService<LdayEntity>{
	/**
	 * 方法描述：通过文件导入日线数据
	 * 作者：Chenxj
	 * 日期：2016年3月29日 - 下午1:15:08
	 * @param ldayFile
	 */
	public void importLdayInfoFromFile(File ldayFile);
	/**
	 * 方法描述：导入文件夹下所有日线数据
	 * 作者：Chenxj
	 * 日期：2016年3月29日 - 下午1:41:33
	 * @param ldayDir
	 */
	public void importLdayInfoFromDir(File ldayDir);
}
