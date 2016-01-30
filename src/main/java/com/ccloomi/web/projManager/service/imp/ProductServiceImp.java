package com.ccloomi.web.projManager.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.web.projManager.entity.ProductEntity;
import com.ccloomi.web.projManager.service.ProductService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProductServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月25日-下午10:07:44
 */
@Service("productService")
public class ProductServiceImp extends GenericService<ProductEntity> implements ProductService{

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		return byPage(map, ProductEntity.class,(sm,m)->sm.SELECT("*").FROM(new ProductEntity(), "p"));
	}

	@Override
	public List<Map<String, Object>> findUserAllProduct(Map<String, Object> map) {
		List<Map<String, Object>>ls=new ArrayList<>();
		
		return ls;
	}
}
