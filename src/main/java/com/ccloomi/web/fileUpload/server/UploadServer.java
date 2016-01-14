package com.ccloomi.web.fileUpload.server;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：UploadServer
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月14日-下午8:47:34
 */
@ServerEndpoint(value="/springSocket/fileup",configurator=SpringConfigurator.class)
public class UploadServer {
	private Session session;
	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		System.out.println("SpringSocket opend.");
	}
	@OnMessage
	public void onTextMessage(String message){
		try {
			session.getBasicRemote().sendText("SpringSocket got message::"+message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
