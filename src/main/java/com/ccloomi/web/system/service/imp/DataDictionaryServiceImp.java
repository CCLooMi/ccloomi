package com.ccloomi.web.system.service.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.web.system.dao.DataDictionaryDao;
import com.ccloomi.web.system.entity.DataDictionaryEntity;
import com.ccloomi.web.system.service.DataDictionaryService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：DataDictionaryServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月19日-下午11:06:22
 */
@Service("dataDictionaryService")
public class DataDictionaryServiceImp extends GenericService<DataDictionaryEntity> implements DataDictionaryService{
	
	@Autowired
	private DataDictionaryDao dataDictionaryDao;
	
	@Override
	public Object save(DataDictionaryEntity entity){
		return dataDictionaryDao.save(entity);
	}
	
	@Override
	public Map<String, Object> findddsByPage(Map<String, Object>map) {
		Map<String, Object>rm=new HashMap<String, Object>();
		DataDictionaryEntity dd=new DataDictionaryEntity();
		int page=-1+(int) map.get("pageNumber");
		int pageSize=(int) map.get("pageSize");
		String keywords=(String) map.get("keywords");
		
		List<Map<String, Object>>rs=new ArrayList<Map<String,Object>>();
		SQLMaker sm=new SQLMaker();
		sm.SELECT("*")
		.FROM(dd, "dd")
		.WHERE("dd.deepIndex=0");
		
		if(keywords!=null){
			sm.WHERE("dd.name LIKE '%?%' OR dd.code LIKE '%?%' OR dd.desc LIKE '%?%'".replaceAll("\\?", keywords));
		}
		
		sm.LIMIT(page*pageSize,pageSize);
		rs=findBySQLGod(sm);
		//在clean之前查询总数据条数
		if(page==0){
			rm.put("totalNumber", countBySQLGod(sm));
		}
		//查询value
		for(Map<String, Object>m:rs){
			List<Map<String, Object>>ls=new ArrayList<Map<String,Object>>();
			sm.clean();
			sm.SELECT("*")
			.FROM(dd, "dd")
			.WHERE("dd.deepIndex=1")
			.AND("dd.pid=?", m.get("id"));
			ls=findBySQLGod(sm);
			m.put("VS", ls);
			m.put("VSS", ls.size());
		}
		rm.put("data", rs);
		return rm;
	}
	
	@Override
	public int removeById(Object id) {
		SQLMaker sm=new SQLMaker();
		sm.DELETE(new DataDictionaryEntity(), "dd")
		.WHERE("dd.id=?", id)
		.OR("dd.pid=?", id);
		return updateBySQLGod(sm);
	}
}
