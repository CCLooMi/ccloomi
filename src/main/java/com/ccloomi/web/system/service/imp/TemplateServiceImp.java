package com.ccloomi.web.system.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.web.system.entity.TemplateEntity;
import com.ccloomi.web.system.service.TemplateService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TemplateServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月17日-下午5:09:28
 */
@Service("templateService")
public class TemplateServiceImp extends GenericService<TemplateEntity> implements TemplateService{

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		return byPage(map, (sm,m)->{
			sm.SELECT("*")
			.FROM(new TemplateEntity(), "t");
		});
	}
	
}
