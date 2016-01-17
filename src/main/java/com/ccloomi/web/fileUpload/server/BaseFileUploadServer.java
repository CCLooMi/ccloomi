package com.ccloomi.web.fileUpload.server;

import java.lang.reflect.ParameterizedType;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.ccloomi.core.component.catche.CacheClient;
import com.ccloomi.core.constant.Constant;
import com.ccloomi.web.fileUpload.bean.FileInfo;
import com.ccloomi.web.fileUpload.bean.UploadCommand;
import com.ccloomi.web.fileUpload.core.BaseFileTarget;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseFileUploadServer
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月14日-下午10:34:38
 */
public abstract class BaseFileUploadServer<T extends BaseFileTarget>{
	protected Logger log=LoggerFactory.getLogger(getClass());
	protected static Map<String,BaseFileTarget>fileTargetMap=new HashMap<>();
	@Autowired
	protected CacheClient cacheClient;
	@Value("${fileUpload.basePath}")
	protected String basePath;
	protected Session session;
	protected UploadCommand command;
	private boolean commandComplete=true;
	private ObjectMapper objMapper;
	private T fileTarget;
	
	@OnOpen
	public void onOpen(Session session){
		this.session=session;
		this.session.setMaxBinaryMessageBufferSize(Constant.blobSize+8);
		this.objMapper=new ObjectMapper();
	}
	@OnMessage
	public void onTextMessage(String message){
		if(!this.commandComplete){
			this.cancelCommand();
		}
		try {
			FileInfo fileInfo=objMapper.readValue(message, FileInfo.class);
			this.fileTarget=getFileTarget(fileInfo);
			this.fileTarget.addUploadServer(this);
			this.command=this.fileTarget.getUploadCommand();
			this.sendAsJsonObject(this.command);
			if(this.command.getCompletePercent()>=1){
				this.commandComplete=true;
			}else{
				this.commandComplete=false;
			}
		} catch (Exception e) {
			log.error("JSON转换异常", e);
		}
	}
	@OnMessage
	public void onBinaryMessage(ByteBuffer bb){
		this.commandComplete=true;
		this.command=this.fileTarget.saveData(bb, this.command);
		this.sendAsJsonObject(this.command);
		this.commandComplete=false;
		if(this.command.getCompletePercent()>=1){
			this.fileTarget.removeUploadServer(this);
			if(this.fileTarget.getUploadServerList().isEmpty()){
				this.removeFileTargetFromCatch(this.fileTarget.getFileInfo().getFileId());
			}
		}
	}
	@OnClose
	public void onClose(){
		cancelCommand();
	}
	@OnError
	public void onError(Throwable t){
		log.error("WebSocket异常", t);
	}
	protected void cancelCommand(){
		if(this.fileTarget!=null&&this.command!=null){
			this.fileTarget.removeUploadServer(this);
			if(this.command.getCompletePercent()<1){
				this.fileTarget.cancelCommand(this.command);
				this.fileTarget.saveProgress();
			}else{
				this.command=null;
			}
			this.fileTarget=null;
		}
	}
	/**
	 * 方法描述：获取T的Class
	 * 作者：Chenxj
	 * 日期：2015年7月6日 - 上午11:25:00
	 * @return Class
	 */
	@SuppressWarnings("unchecked")
	protected Class<T> getTClass(){
		return (Class<T>)((ParameterizedType)this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	/**
	 * 描述：获取T的实例
	 * 作者：Chenxj
	 * 日期：2015年11月10日 - 下午10:35:25
	 * @return
	 */
	protected T TInstance(){
		try {
			return getTClass().newInstance();
		} catch (Exception e) {
			log.error("获取T实例异常", e);
			return null;
		}
	}
	private T getFileTarget(FileInfo fileInfo){
		T fileTarget=getFileTargetFromCatch(fileInfo.getFileId());
		if(fileTarget==null){
			fileTarget=TInstance();
			fileTarget.initTarget(fileInfo, basePath);
			fileTarget.saveProgress();
			saveFileTargetTOCatch(fileInfo.getFileId(), fileTarget);
		}
		return fileTarget;
	}
	@SuppressWarnings("unchecked")
	private T getFileTargetFromCatch(String fileId){
//		return cacheClient.get(fileId, this.getTClass());
		return (T) fileTargetMap.get(fileId);
	}
	private void saveFileTargetTOCatch(String key,BaseFileTarget fileTarget){
		fileTargetMap.put(key, fileTarget);
	}
	private void removeFileTargetFromCatch(String key){
		fileTargetMap.remove(key);
	}
	private BaseFileUploadServer<T> send(String data){
		try{
			this.session.getBasicRemote().sendText(data);
		}catch(Exception e){
			log.error("数据发送异常", e);
		}
		return this;
	}
	private BaseFileUploadServer<T> sendAsJsonObject(Object obj){
		try {
			return send(objMapper.writeValueAsString(obj));
		} catch (JsonProcessingException e) {
			log.error("数据转换异常", e);
			return this;
		}
	}
}
