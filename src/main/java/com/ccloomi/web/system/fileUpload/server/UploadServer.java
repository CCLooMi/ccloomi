package com.ccloomi.web.system.fileUpload.server;

import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.ccloomi.core.component.fileUpload.server.BaseFileUploadServer;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：UploadServer
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月14日-下午8:47:34
 */
@ServerEndpoint(value="/springSocket/fileup",configurator=SpringConfigurator.class)
public class UploadServer extends BaseFileUploadServer<SimpleFileTarget>{
	
}
