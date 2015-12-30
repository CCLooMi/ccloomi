package com.ccloomi.web.projManager.controller;

import java.util.Date;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.projManager.entity.ProductEntity;
import com.ccloomi.web.projManager.service.ProductService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProductController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月25日-下午10:09:29
 */
@Controller
@RequestMapping("/product")
public class ProductController extends BaseController{
	@Autowired
	private ProductService productService;
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>findByPage(@RequestBody Map<String, Object>map){
		return productService.findByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody ProductEntity product){
		product.setCreatedDate(new Date());
		product.setCreatedBy(currentUser().getUsername());
		product.setIdUser(currentUser().getId());
		Object id=productService.save(product);
		if(id!=null){
			return responseMessageSuccess(id);
		}else{
			return responseMessageError("添加产品失败");
		}
	}
}
