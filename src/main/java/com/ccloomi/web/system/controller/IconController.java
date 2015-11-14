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
import com.ccloomi.web.system.entity.IconEntity;
import com.ccloomi.web.system.service.IconService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：IconController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月14日-下午8:14:14
 */
@Controller
@RequestMapping("/icon")
public class IconController extends BaseController{
	@Autowired
	private IconService iconService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>byPage(@RequestBody Map<String, Object>map){
		return iconService.findIconsByPage(map);
	}
	@RequestMapping("/add")
	@ResponseBody
	@RequiresAuthentication
	public Message add(@RequestBody IconEntity icon){
		Object id=iconService.save(icon);
		if(id==null){
			return responseMessageError("添加图标失败");
		}else{
			return responseMessageSuccess(id);
		}
	}
	@RequestMapping("/update")
	@ResponseBody
	@RequiresAuthentication
	public Message update(@RequestBody IconEntity icon){
		int i=iconService.update(icon);
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("修改图标失败");
		}
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody IconEntity icon){
		int i=iconService.delete(icon.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("删除图标失败");
		}
	}
}
