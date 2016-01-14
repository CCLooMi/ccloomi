package com.ccloomi.web.fileUpload.server;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.component.catche.CacheClient;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseFileUploadServer
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月14日-下午10:34:38
 */
public abstract class BaseFileUploadServer {
	@Autowired
	protected CacheClient cacheClient;
	
}
