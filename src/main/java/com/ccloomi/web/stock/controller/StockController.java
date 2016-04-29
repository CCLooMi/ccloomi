package com.ccloomi.web.stock.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ccloomi.core.common.bean.Message;
import com.ccloomi.core.common.controller.BaseController;
import com.ccloomi.core.component.progress.ProgressBean;
import com.ccloomi.core.util.ExcelUtil;
import com.ccloomi.web.stock.bean.KmlBean;
import com.ccloomi.web.stock.entity.StockEntity;
import com.ccloomi.web.stock.service.ListedCompanyService;
import com.ccloomi.web.stock.service.StockService;
import com.ccloomi.web.system.constant.DDConstant;
import com.ccloomi.web.system.constant.StockNameConstant;
import com.ccloomi.web.system.freemarker.DBTemplateLoader;
import com.ccloomi.web.system.progress.SystemProgress;

import freemarker.template.Configuration;
import freemarker.template.Template;

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
	@Autowired
	private ListedCompanyService listedCompanyService;
	
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
		return responseMessageSuccess(stockService.syncCoordinates(stock));
	}
	@RequestMapping("/syncAllCoordinates")
	@ResponseBody
	@RequiresAuthentication
	public Message syncAllCoordinates(){
		Map<String, String>stockMap=StockNameConstant.getStockNameConstantMap();
		float complete=0;
		float total=stockMap.keySet().size();
		for(String idStock:stockMap.keySet()){
			StockEntity stock=stockService.getById(idStock);
			stockService.syncCoordinates(stock);
			complete++;
			//发送进度
			ProgressBean progeressBean=new ProgressBean();
			progeressBean.setType("syncAllCoordinates");
			progeressBean.setProgress(complete/total);
			progeressBean.setTitle("同步所有经纬信息");
			SystemProgress.updateProgress(progeressBean);
		}
		return responseMessageSuccess();
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
	@RequestMapping("/gaodeMap")
	@ResponseBody
	@RequiresAuthentication
	public Map<String, String>gaodeMap(){
		return DDConstant.gaodeMap();
	}
	@RequestMapping("/downloadStockMapExcelTemplate")
	@ResponseBody
	@RequiresAuthentication
	public void downloadStockMapExcelTemplate(HttpServletResponse response){
		List<Map<String, Object>>dataMap=listedCompanyService.getMapExcelTemplateData();
//		response.setContentType("application/vnd.ms-excel");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename=excelTemplate.xlsx");
		try{
			Map<String, List<Map<String,Object>>>dm=new HashMap<>();
			dm.put("云图基础模版",dataMap);
			ExcelUtil.createExcel(dm, null, response.getOutputStream(), 2);
			response.getOutputStream().flush();
		}catch(Exception e){
			log.error("模版生成出现异常", e);
		}
		
	}
	@RequestMapping("/downloadStockMapAsGoogleKML")
	@ResponseBody
	@RequiresAuthentication
	public void downloadStockMapAsGoogleKML(HttpServletResponse response){
		List<Map<String, Object>>dataMap=listedCompanyService.getMapKMLTemplateData();
		response.setHeader("Content-Disposition", "attachment; filename=CCLooMi-StockMap.kml");
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
		cfg.setTemplateLoader(new DBTemplateLoader());
		try {
			Template t=cfg.getTemplate("google-kml");
			KmlBean kml=new KmlBean();
			kml.addByMapList(dataMap);
			System.out.println(kml);
			t.process(kml, response.getWriter());
		}catch (Exception e) {
			log.error("KML文件生成异常", e);
		}
	}
}
