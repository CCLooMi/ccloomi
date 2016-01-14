package com.ccloomi.web.fileUpload.server;

import java.io.IOException;

import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

/**深圳市设计同道技术有限公司
 * 类    名：UploadServer
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2016年1月14日-下午1:49:45
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
