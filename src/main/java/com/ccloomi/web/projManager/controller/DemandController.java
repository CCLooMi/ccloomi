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
import com.ccloomi.web.projManager.service.DemandService;
import com.ccloomi.web.system.entity.UserEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DemandController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月23日-下午3:27:18
 */
@Controller
@RequestMapping("/demand")
public class DemandController extends BaseController{
	@Autowired
	private DemandService demandService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>byPage(@RequestBody Map<String, Object>map){
		return demandService.findByPage(map);
	}

	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody Map<String, Object>map){
		UserEntity user=currentUser();
		map.put("createdBy", user.getUsername());
		map.put("stage", 0);
		map.put("status", 0);
		map.put("createdDate", new Date());
		Object id=demandService.add(map);
		if(id!=null){
			return responseMessageSuccess(id);
		}else{
			return responseMessageError("添加需求失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody Map<String, Object>map){
		boolean isOK=demandService.remove(map);
		if(isOK){
			return responseMessageSuccess();
		}else{
			return responseMessageError("删除需求失败");
		}
	}
}
