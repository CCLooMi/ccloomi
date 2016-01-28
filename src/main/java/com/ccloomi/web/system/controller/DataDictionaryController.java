package com.ccloomi.web.system.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.web.system.entity.DataDictionaryEntity;
import com.ccloomi.web.system.service.DataDictionaryService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：DataDictionaryController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月14日-下午9:05:04
 */
@Controller
@RequestMapping("/dd")
public class DataDictionaryController extends BaseController{
	@Autowired
	private DataDictionaryService dataDictionaryService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> ddsByPage(@RequestBody Map<String, Object>map){
		return dataDictionaryService.findddsByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody DataDictionaryEntity dd){
		Object id = dataDictionaryService.save(dd);
		if(id==null){
			return responseMessageError("添加失败");
		}else{
			return responseMessageSuccess(id);
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	@RequiresAuthentication
	public Message update(@RequestBody DataDictionaryEntity dd){
		int i=dataDictionaryService.update(dd);
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("更新失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody DataDictionaryEntity dd){
		int i=dataDictionaryService.removeById(dd.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("删除失败");
		}
	}
	@RequestMapping("/findByCode")
	@ResponseBody
	@RequiresAuthentication
	public List<Map<String, Object>>findByCode(@RequestBody Map<String, Object>map){
		return dataDictionaryService.findByCode(map.get("code"));
	}
}
