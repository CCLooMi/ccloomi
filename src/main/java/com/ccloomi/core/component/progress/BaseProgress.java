package com.ccloomi.core.component.progress;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.constant.Constant;
import com.fasterxml.jackson.databind.ObjectMapper;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseProgress
 * 类 描 述：进度基类
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-下午12:16:29
 */
public abstract class BaseProgress {
	protected Logger log=LoggerFactory.getLogger(getClass());
	protected static List<BaseProgress>progressList=new ArrayList<>();
	protected Session session;
	private ObjectMapper objMapper;
	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		this.session.setMaxBinaryMessageBufferSize(Constant.blobSize+8);
		this.objMapper=new ObjectMapper();
		progressList.add(this);
	}
	@OnClose
	public void onClose(){
		progressList.remove(this);
	}
	@OnError
	public void onError(Throwable t){
		progressList.remove(this);
		log.error("WebSocket异常", t);
	}
	private void sendObject(Object obj){
		try {this.session.getBasicRemote().sendText(objMapper.writeValueAsString(obj));}
		catch (IOException e) {
			progressList.remove(this);
		}
	}
	/**
	 * 描述：更新进度
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 下午12:50:38
	 * @param progressBean
	 */
	public static void updateProgress(ProgressBean progressBean){
		for(BaseProgress bp:progressList){
			bp.sendObject(progressBean);
		}
	}
}
