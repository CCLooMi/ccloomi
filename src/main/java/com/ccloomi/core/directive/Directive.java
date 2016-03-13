package com.ccloomi.core.directive;

import com.ccloomi.core.common.entity.IdEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：Directive
 * 类 描 述：指令接口
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-下午2:03:05
 */
public interface Directive <T extends IdEntity>{
	public void doUpdate();
	public void doRollback();
}
