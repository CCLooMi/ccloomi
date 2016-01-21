package com.ccloomi.web.system.log;

import java.io.IOException;

import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.ccloomi.core.component.log.WebSocketAppender;

@ServerEndpoint(value="/socket/log",configurator=SpringConfigurator.class)
public class SocketAppender extends WebSocketAppender{

	@Override
	public void start(){
		if(this.encoder==null){
			addError("No encoder set for the appender named ["+ name +"].");
			return;
		}
		try{encoder.init(System.out);}
		catch(IOException e){}
		super.start();
	}
}
