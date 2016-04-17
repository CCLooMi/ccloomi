package com.ccloomi.web.system.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.system.entity.TemplateEntity;
import com.ccloomi.web.system.service.TemplateService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TemplateController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月17日-下午5:36:34
 */
@Controller
@RequestMapping("/template")
public class TemplateController extends BaseController{
	@Autowired
	private TemplateService templateService;
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>byPage(@RequestBody Map<String, Object>map){
		return templateService.findByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody TemplateEntity template){
		Object id=templateService.save(template);
		if(id==null){
			return responseMessageError("添加模版失败");
		}else{
			return responseMessageSuccess(template);
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	@RequiresAuthentication
	public Message update(@RequestBody TemplateEntity template){
		int i=templateService.update(template);
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("更新模版失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody TemplateEntity template){
		int i=templateService.delete(template.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("删除模版失败");
		}
	}
}
