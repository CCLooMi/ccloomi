package com.ccloomi.web.projManager.bean;

import java.util.List;
import java.util.Map;

import com.ccloomi.web.projManager.entity.ProductEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ProductBean
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月14日-下午8:43:57
 */
public class ProductBean extends ProductEntity{
	private static final long serialVersionUID = 3997003280105839188L;
	private Map<String, List<Object>>whiteListObject;
	/**获取 whiteListObject*/
	public Map<String, List<Object>> getWhiteListObject() {
		return whiteListObject;
	}
	/**设置 whiteListObject*/
	public void setWhiteListObject(Map<String, List<Object>> whiteListObject) {
		this.whiteListObject = whiteListObject;
	}
}
