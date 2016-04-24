package com.ccloomi.web.system.progress;

import javax.websocket.server.ServerEndpoint;

import org.springframework.web.socket.server.standard.SpringConfigurator;

import com.ccloomi.core.component.progress.BaseProgress;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：SystemProgress
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-下午12:53:36
 */
@ServerEndpoint(value="/springSocket/progress",configurator=SpringConfigurator.class)
public class SystemProgress extends BaseProgress{
	
}
