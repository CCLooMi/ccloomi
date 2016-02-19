package com.ccloomi.core.component.log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.AppenderBase;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：WebSocketAppender
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月20日-下午10:52:50
 */
public abstract class WebSocketAppender extends AppenderBase<ILoggingEvent>{
	protected static List<WebSocketAppender>appenders=new ArrayList<>();
	protected PatternLayoutEncoder encoder;
	protected Session session;
	/**获取 appenders*/
	public static List<WebSocketAppender> getAppenders() {
		return appenders;
	}
	/**设置 appenders*/
	public static void setAppenders(List<WebSocketAppender> appenders) {
		WebSocketAppender.appenders = appenders;
	}
	/**获取 encoder*/
	public PatternLayoutEncoder getEncoder() {
		return encoder;
	}
	/**设置 encoder*/
	public void setEncoder(PatternLayoutEncoder encoder) {
		this.encoder = encoder;
	}
	public void sendMessage(String message){
		try {this.session.getBasicRemote().sendText(message);}
		catch (IOException e) {
			WebSocketAppender.appenders.add(this);
		}
	}
	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		WebSocketAppender.appenders.add(this);
	}
	@OnClose
	public void onClose(){
		WebSocketAppender.appenders.remove(this);
	}
	@OnError
	public void onError(Throwable t){
		WebSocketAppender.appenders.remove(this);
	}
	@Override
	protected void append(ILoggingEvent eventObject) {
		for(WebSocketAppender appender:WebSocketAppender.appenders){
			appender.sendMessage(this.encoder.getLayout().doLayout(eventObject));
		}
	}
}
