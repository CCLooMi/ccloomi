package com.ccloomi.web.system.dao.imp;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.dao.GenericDao;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.system.dao.DataDictionaryDao;
import com.ccloomi.web.system.entity.DataDictionaryEntity;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：DataDictionaryDaoImp
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月19日-下午10:50:33
 */
@Service("dataDictionaryDao")
public class DataDictionaryDaoImp extends GenericDao<DataDictionaryEntity> implements DataDictionaryDao{
	@Override
	public Object save(DataDictionaryEntity entity){
		if(entity.getId()==null){
			entity.setId(StringUtil.buildUUID());
			if(entity.getPid()==null){
				entity.setRootId(entity.getId());
				entity.setDeepIndex(0);
			}
		}
		Object id=super.save(entity);
		return id;
	}
}
