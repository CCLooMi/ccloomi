package com.ccloomi.core.component.sms;

import com.ccloomi.core.component.sms.bean.SenderResponseBean;
import com.ccloomi.core.component.sms.bean.Sms;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：SmsSender
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午10:50:37
 */
public interface SmsSender {
	public SenderResponseBean send(Sms sms);
}
