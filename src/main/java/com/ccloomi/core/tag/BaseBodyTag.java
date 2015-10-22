package com.ccloomi.core.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：BaseBodyTag
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月26日-上午12:26:02
 */
public abstract class BaseBodyTag extends BodyTagSupport{
	private static final long serialVersionUID = 1859665499177050007L;
	protected JspWriter out;
	
	@Override
	public int doStartTag() throws JspException {
		out=pageContext.getOut();
		try {
			startTag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_BODY_BUFFERED;
	}
	
	@Override
	public int doAfterBody() throws JspException {
		try {
			afterBodyTag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	
	@Override
	public int doEndTag() throws JspException {
		try {
			endTag();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}
	/**
	 * 描述：TAG之前
	 * 作者：Chenxj
	 * 日期：2015年9月26日 - 上午12:35:40
	 * @throws Exception
	 */
	protected abstract void startTag() throws Exception;
	/**
	 * 描述：TAG之中
	 * 作者：Chenxj
	 * 日期：2015年9月26日 - 上午12:36:00
	 * @throws JspException
	 */
	protected abstract void afterBodyTag() throws Exception;
	/**
	 * 描述：TAG之后
	 * 作者：Chenxj
	 * 日期：2015年9月26日 - 上午12:36:05
	 * @throws Exception
	 */
	protected abstract void endTag() throws Exception;
}
