package com.ccloomi.web.projManager.service.imp;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.service.ByPageSelect;
import com.ccloomi.core.common.service.GenericService;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.util.JSONUtil;
import com.ccloomi.web.projManager.dao.DemandDao;
import com.ccloomi.web.projManager.dao.DemandDetailDao;
import com.ccloomi.web.projManager.entity.DemandDetailEntity;
import com.ccloomi.web.projManager.entity.DemandEntity;
import com.ccloomi.web.projManager.service.DemandService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DemandServiceImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月10日-下午10:31:19
 */
@Service("demandService")
public class DemandServiceImp extends GenericService<DemandEntity> implements DemandService{
	
	@Autowired
	private DemandDao demandDao;
	@Autowired
	private DemandDetailDao demandDetailDao;
	
	@Override
	public Map<String, Object> findByPage(Map<String, Object> map) {
		return byPage(map, DemandEntity.class, new ByPageSelect() {
			@Override
			public void doSelect(SQLMaker sm, Map<String, Object> map) {
				sm.SELECT("*")
				.FROM(new DemandEntity(), "d");
				if(map.get("idProduct")!=null){
					sm.WHERE("d.idProduct=?", map.get("idProduct"));
				};
			}
		});
	}

	@Override
	public Object add(Map<String, Object> map) {
		DemandEntity demand=JSONUtil.convertMapToBean(map, DemandEntity.class);
		DemandDetailEntity demandDetail=JSONUtil.convertMapToBean(map, DemandDetailEntity.class);
		Object id=demandDao.save(demand);
		if(id!=null){
			demandDetail.setId((String)id);
			demandDetailDao.add(demandDetail);
		}
		return id;
	}

	@Override
	public boolean remove(Map<String, Object> map) {
		int i=demandDao.delete(map.get("id"));
		i+=demandDetailDao.delete(map.get("id"));
		return i>0?true:false;
	}
}
