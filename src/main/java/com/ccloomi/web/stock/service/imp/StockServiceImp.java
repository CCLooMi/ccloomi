package com.ccloomi.web.stock.service.imp;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.util.ExcelUtil;
import com.ccloomi.core.util.HttpUtil;
import com.ccloomi.core.util.JSONUtil;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.core.util.TemplateParserUtil;
import com.ccloomi.web.stock.dao.ListedCompanyDao;
import com.ccloomi.web.stock.dao.StockDao;
import com.ccloomi.web.stock.dao.TagDao;
import com.ccloomi.web.stock.entity.ListedCompanyEntity;
import com.ccloomi.web.stock.entity.StockEntity;
import com.ccloomi.web.stock.entity.TagEntity;
import com.ccloomi.web.stock.service.StockService;
import com.ccloomi.web.system.constant.DDConstant;
import com.ccloomi.web.system.constant.ListedCompanyNameConstant;
import com.ccloomi.web.system.constant.TagConstant;
import com.ccloomi.web.system.constant.TemplateConstant;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StockServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-上午10:00:14
 */
@Service("stockService")
public class StockServiceImp extends GenericService<StockEntity> implements StockService{
	@Autowired
	private StockDao stockDao;
	@Autowired
	private TagDao tagDao;
	@Autowired
	private ListedCompanyDao listedCompanyDao;
	@Override
	public void importStockInfoFromFile(File excelFile) {
		List<List<Object>>dataList=ExcelUtil.readOneSheetFromExcel(excelFile);
		//删除第一行
		dataList.remove(0);
		//tag和id的map
		Map<String, String>name2IdMap=TagConstant.getName2IdMap();
		for(List<Object>args:dataList){
			//处理最好一个tag
			String tag=String.valueOf(args.remove(args.size()-1));
			//存储tag，没有就添加到数据库
			if(!name2IdMap.containsKey(tag)){
				TagEntity tagEntity=new TagEntity();
				tagEntity.setId(StringUtil.buildUUID());
				tagEntity.setName(tag);
				tagDao.add(tagEntity);
				name2IdMap.put(tag, tagEntity.getId());
				args.add(tagEntity.getId());
			}else{
				//已有该tag则直接取该tagId
				args.add(name2IdMap.get(tag));
			}
			//保存股票信息
			StockEntity stockEntity=new StockEntity();
			stockEntity.setId(String.valueOf(args.get(0)));
			stockEntity.setName(String.valueOf(args.get(1)));
			stockEntity.setType(String.valueOf(args.get(2)));
			stockEntity.setArea(String.valueOf(args.get(3)));
			stockEntity.setIndustry(String.valueOf(args.get(4)));
			stockDao.saveOrUpdate(stockEntity);
		}
		
	}

	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		Map<String, Object>result=byPage(map, (sm,m)->{
			sm.SELECT("s.id")
			.SELECT("s.name")
			.SELECT("s.industry")
			.SELECT("s.idListedCompany")
			.SELECT_AS("lc.id", "companyId")
			.SELECT_AS("lc.name", "company")
			.SELECT("lc.mainBusiness")
			.SELECT("lc.registeredAddress")
			.SELECT("lc.issuePrice")
			.SELECT("lc.chairman")
			.SELECT("lc.secretaries")
			.SELECT("lc.listedDate")
			.SELECT("lc.actualController")
			.FROM(new StockEntity(), "s")
			.LEFT_JOIN(new ListedCompanyEntity(), "lc", "s.idListedCompany=lc.id");
		});
		@SuppressWarnings("unchecked")
		List<Map<String, Object>>data=(List<Map<String, Object>>) result.get("data");
		for(Map<String, Object>mm:data){
			mm.put("industry", TagConstant.getId2NameMap().get(mm.get("industry")));
			mm.put("idListedCompany", ListedCompanyNameConstant.getListedCompanyNameConstantMap().get(mm.get("idListedCompany")));
		}
		return result;
	}

	@Override
	public ListedCompanyEntity syncCompanyInfo(StockEntity stock) {
		String template=TemplateConstant.getTemplateConstantMap().get("ths-company-info");
		String dataUrl=DDConstant.thsMap().get("ths-company-info").replaceFirst("\\{[^\\{\\}]+\\}", String.valueOf(stock.getId()));
		String htmlData=HttpUtil.getHtmlAsString(dataUrl);
		Map<String, String>dataMap=TemplateParserUtil.parserHtmlTemplate2Map(htmlData, template);
		ListedCompanyEntity listedCompany=JSONUtil.convertMapToBean(dataMap, ListedCompanyEntity.class);
		Map<String, String>listedCompanyNameMap=ListedCompanyNameConstant.getListedCompanyNameConstantMap();
		//检查公司名是否已经存在
		if(!listedCompanyNameMap.containsKey(listedCompany.getName())){
			listedCompany.setId(StringUtil.buildUUID());
			try{
				listedCompany.setIssuePrice(Float.valueOf(dataMap.get("other").replaceAll("元", "").split("/", 2)[0]));
			}catch(Exception e){
				listedCompany.setIssuePrice(-1f);
			}
			listedCompanyDao.saveOrUpdate(listedCompany);
			//将公司名称和ID保存到map中
			listedCompanyNameMap.put(listedCompany.getId(), listedCompany.getName());
			listedCompanyNameMap.put(listedCompany.getName(), listedCompany.getId());
		}else{
			listedCompany.setId(listedCompanyNameMap.get(listedCompany.getName()));
		}
		if(stock.getIdListedCompany()==null||"".equals(stock.getIdListedCompany())){
			//更新股票的公司ID
			StockEntity st=new StockEntity();
			st.setId(stock.getId());
			st.setIdListedCompany(listedCompany.getId());
			stockDao.lazyUpdate(st);
		}
		return listedCompany;
	}
}
