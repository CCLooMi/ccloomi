package com.ccloomi.core.component.progress;

import com.ccloomi.core.common.bean.BaseBean;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ProgessBean
 * 类 描 述：进度bean对象
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-下午12:42:49
 */
public class ProgressBean extends BaseBean{
	private static final long serialVersionUID = 7474498849782087533L;
	/**进度ID*/
	private String id;
	/**进度标题*/
	private String title;
	/**进度类型*/
	private String type;
	/**进度值*/
	private float progress;
	/**获取 进度ID*/
	public String getId() {
		return id;
	}
	/**设置 进度ID*/
	public void setId(String id) {
		this.id = id;
	}
	/**获取 进度标题*/
	public String getTitle() {
		return title;
	}
	/**设置 进度标题*/
	public void setTitle(String title) {
		this.title = title;
	}
	/**获取 进度类型*/
	public String getType() {
		return type;
	}
	/**设置 进度类型*/
	public void setType(String type) {
		this.type = type;
	}
	/**获取 进度值*/
	public float getProgress() {
		return progress;
	}
	/**设置 进度值*/
	public void setProgress(float progress) {
		this.progress = progress;
	}
}
