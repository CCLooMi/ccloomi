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
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.DbUtil;
import com.ccloomi.web.dbManager.bean.VisNetworkBean;
import com.ccloomi.web.dbManager.entity.CharacterSetsEntity;
import com.ccloomi.web.dbManager.entity.CollationsEntity;
import com.ccloomi.web.dbManager.entity.ColumnsEntity;
import com.ccloomi.web.dbManager.entity.SchemataEntity;
import com.ccloomi.web.dbManager.service.ColumnsService;
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
	@Autowired
	private ColumnsService columnsService;
	
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
		 SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		 sm.CREATE_DATABASE(schemata.getSchema_name());
		 if(schemata.getDefault_character_set_name()!=null){
			 sm.DEFAULT_CHARACTER_SET(schemata.getDefault_character_set_name());
		 }
		 if(schemata.getDefault_collation_name()!=null){
			 sm.COLLATE(schemata.getDefault_collation_name());
		 }
		 int i=schemataService.updateBySQLGod(sm);
		 if(i>0){
			 return responseMessageSuccess();
		 }
		return responseMessageError("创建数据库失败");
	}
	@RequestMapping("/allCharacterSets")
	@ResponseBody
	@RequiresAuthentication
	public List<CharacterSetsEntity> AllCharacterSets(){
		return schemataService.findCharacterSets();
	}
	@RequestMapping("/collationsByCharacter")
	@ResponseBody
	@RequiresAuthentication
	public List<CollationsEntity> collationsByCharacter(String characterName){
		return schemataService.findCollationsByCharacter(characterName);
	}
	@RequestMapping("/convertTableColumns2Properties")
	@ResponseBody
	@RequiresAuthentication
	public List<String> convertTableColumns2Properties(String dbName,String tableName){
		List<ColumnsEntity>columns=columnsService.findColumnsByTableSchemaAndTableName(dbName, tableName);
		return DbUtil.convertColumns2Properties(columns,(ColumnsEntity column)->{
			//此处作过滤操作
		});
	}
}
