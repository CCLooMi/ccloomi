package com.ccloomi.web.projManager.dao.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.GenericDao;
import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.projManager.dao.WhiteListDao;
import com.ccloomi.web.projManager.entity.WhiteListEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：WhiteListDaoImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年2月14日-下午8:56:07
 */
@Service("whiteListDao")
public class WhiteListDaoImp extends GenericDao<WhiteListEntity> implements WhiteListDao{

	@Override
	public void batchUpdateByDeleteUpdateAddMap(Map<String, List<Object>> deleteUpdateAddMap, Object targetId) {
		List<Object>del_list=deleteUpdateAddMap.get("del");
		List<Object>upd_list=deleteUpdateAddMap.get("upd");
		List<Object>add_list=deleteUpdateAddMap.get("add");
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		if(del_list.size()>0){
			sm.DELETE(new WhiteListEntity(), "wl")
			.WHERE("wl.idTarget=?")
			.AND("wl.idRole=?");
			List<Object[]>batchArgs=new ArrayList<>();
			for(Object r:del_list){
				List<Object>args=new ArrayList<>();
				args.add(targetId);
				args.add(r);
				batchArgs.add(args.toArray());
			}
			sm.setBatchArgs(batchArgs);
			batchUpdateBySQLGod(sm);
			sm.clean();
		}
		if(upd_list.size()>0){
			sm.UPDATE(new WhiteListEntity(), "wl")
			.SET("wl.idRole=?")
			.WHERE("wl.idTarget=?")
			.AND("wl.idRole=?");
			List<Object[]>batchArgs=new ArrayList<>();
			for(Object r:upd_list){
				List<Object>args=new ArrayList<>();
				@SuppressWarnings("unchecked")
				Map<String, Object>m=(Map<String, Object>) r;
				args.add(m.get("to"));
				args.add(targetId);
				args.add(m.get("from"));
				batchArgs.add(args.toArray());
			}
			sm.setBatchArgs(batchArgs);
			batchUpdateBySQLGod(sm);
			sm.clean();
		}
		if(add_list.size()>0){
			List<WhiteListEntity>whiteList=new ArrayList<>();
			for(Object r:add_list){
				WhiteListEntity wl=new WhiteListEntity();
				wl.setId(StringUtil.buildUUID());
				wl.setIdRole(String.valueOf(r));
				wl.setIdTarget(String.valueOf(targetId));
				wl.setType(0);
				whiteList.add(wl);
			}
			batchSave(whiteList);
			sm.clean();
		}
	}
	
}
