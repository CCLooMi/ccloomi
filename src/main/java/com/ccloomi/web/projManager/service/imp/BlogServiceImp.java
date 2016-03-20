package com.ccloomi.web.projManager.service.imp;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.ByPageSelect;
import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.web.projManager.entity.BlogEntity;
import com.ccloomi.web.projManager.service.BlogService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月20日-下午6:38:57
 */
@Service("blogService")
public class BlogServiceImp extends GenericService<BlogEntity> implements BlogService{

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		return byPage(map, BlogEntity.class,new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				sm.SELECT("*")
				.FROM(new BlogEntity(), "b");
				if(map.get("idUser")!=null){
					sm.AND("b.idUser=?", map.get("idUser"));
				};
				sm.ORDER_BY("b.datetime DESC");
			}
		});
	}
	
}
