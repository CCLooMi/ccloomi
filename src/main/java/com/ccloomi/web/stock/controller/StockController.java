package com.ccloomi.web.stock.controller;

import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.core.component.progress.ProgressBean;
import com.ccloomi.web.stock.entity.StockEntity;
import com.ccloomi.web.stock.service.StockService;
import com.ccloomi.web.system.constant.StockNameConstant;
import com.ccloomi.web.system.progress.SystemProgress;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StockController
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月16日-下午11:24:36
 */
@Controller
@RequestMapping("/stock")
public class StockController extends BaseController{
	
	@Autowired
	private StockService stockService;
	
	@RequestMapping("/byPage")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, Object>findByPage(@RequestBody Map<String, Object>map){
		return stockService.findByPage(map);
	}
	@RequestMapping("/syncCompanyInfo")
	@ResponseBody
	@RequiresAuthentication
	public Message syncCompanyInfo(@RequestBody StockEntity stock){
		return responseMessageSuccess(stockService.syncCompanyInfo(stock));
	}
	@RequestMapping("/syncAllCompanyInfo")
	@ResponseBody
	@RequiresAuthentication
	public Message syncAllCompanyInfo(){
		Map<String, String>stockMap=StockNameConstant.getStockNameConstantMap();
		float complete=0;
		float total=stockMap.keySet().size();
		for(String idStock:stockMap.keySet()){
			StockEntity stock=stockService.getById(idStock);
			if(stock.getIdListedCompany()==null||"".equals(stock.getIdListedCompany())){
				stockService.syncCompanyInfo(stock);
			}
			complete++;
			//发送进度
			ProgressBean progeressBean=new ProgressBean();
			progeressBean.setType("syncAllCompanyInfo");
			progeressBean.setProgress(complete/total);
			progeressBean.setTitle("同步所有公司信息");
			SystemProgress.updateProgress(progeressBean);
		}
		return responseMessageSuccess();
	}
	@RequestMapping("/syncCoordinates")
	@ResponseBody
	@RequiresAuthentication
	public Message syncCoordinates(@RequestBody StockEntity stock){
		return null;
	}
	@RequestMapping("/syncAllCoordinates")
	@ResponseBody
	@RequiresAuthentication
	public Message syncAllCoordinates(){
		return null;
	}
	@RequestMapping("/remove")
	@ResponseBody
	@RequiresAuthentication
	public Message remove(@RequestBody StockEntity stock){
		int i=stockService.delete(stock.getId());
		if(i>0){
			return responseMessageSuccess();
		}else{
			return responseMessageError("删除股票失败");
		}
	}
}
