package com.ccloomi.web.dbManager.controller;

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
import com.ccloomi.web.dbManager.bean.VisNetworkBean;
import com.ccloomi.web.dbManager.entity.SchemataEntity;
import com.ccloomi.web.dbManager.service.SchemataService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：DbController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月23日-下午9:56:20
 */
@Controller
@RequestMapping("/db")
public class DbController extends BaseController{
	@Autowired
	private SchemataService schemataService;
	
	@RequestMapping("/asVisNetwork")
	@ResponseBody
	@RequiresAuthentication
	public VisNetworkBean findAsVisNetworkBySchemaName(String name){
		return schemataService.findAsVisNetworkBySchemaName(name);
	}
	@RequestMapping("/c2c")
	@ResponseBody
	@RequiresAuthentication
	public List<Map<String, Object>>findColumn2ColumnAsVisNetworkEdgesBySchemaName(String name){
		return schemataService.findColumn2ColumnAsVisNetworkEdgesBySchemaName(name);
	}
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object> schematasByPage(@RequestBody Map<String, Object>map){
		return schemataService.findSchematasByPage(map);
	}
	@RequestMapping("/createDb")
	@ResponseBody
	@RequiresAuthentication
	public Message createDb(@RequestBody SchemataEntity schemata){
//		String sql="CREATE DATABASE `test2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci";
		return responseMessageSuccess();
	}
}
