package com.ccloomi.web.projManager.service;

import java.util.Map;

import com.ccloomi.core.common.service.BaseService;
import com.ccloomi.web.projManager.bean.ProductBean;
import com.ccloomi.web.projManager.entity.ProductEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProductService
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月25日-下午10:06:43
 */
public interface ProductService extends BaseService<ProductEntity>{
	public Map<String, Object>findByPage(Map<String, Object>map);
	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年2月20日 - 上午8:57:31
	 * @param p
	 * @return
	 */
	public Object saveProduct(ProductBean p);
	/**描述：
	 * 作者：Chenxj
	 * 日期：2016年2月20日 - 上午11:00:48
	 * @param p
	 * @return
	 */
	public int updateProduct(ProductBean p);
}
