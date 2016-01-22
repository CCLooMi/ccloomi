package com.ccloomi.web.system.log;

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
		}else{
			this.encoder.setPattern("<span class=\"datetime\">%d{yyyy-MM-dd HH:mm:ss.SSS}</span><span class=\"thread\">[%thread]</span><span class=\"level\">%-5level</span><span class=\"logger\">%logger{5}</span> :: <span class=\"msg\">%msg</span>%n");
			this.encoder.start();
		}
		super.start();
	}
}
