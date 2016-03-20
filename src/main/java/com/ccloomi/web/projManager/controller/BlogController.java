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
import com.ccloomi.web.projManager.entity.BlogEntity;
import com.ccloomi.web.projManager.service.BlogService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BlogController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月20日-下午6:32:04
 */
@Controller
@RequestMapping("/blog")
public class BlogController extends BaseController{
	
	@Autowired
	private BlogService blogService;

	@ResponseBody
	@RequestMapping("/byPage")
	@RequiresAuthentication
	public Map<String, Object> byPage(@RequestBody Map<String, Object> map) {
		map.put("idUser", currentUser().getId());
		return blogService.findByPage(map);
	}
	@ResponseBody
	@RequestMapping("/add")
	@RequiresAuthentication
	public Message add(@RequestBody BlogEntity blog){
		blog.setDatetime(new Date());
		blog.setIdUser(currentUser().getId());
		Object id=blogService.save(blog);
		if(id!=null){
			return responseMessageSuccess(blog);
		}else{
			return responseMessageError("保存失败");
		}
	}
}
