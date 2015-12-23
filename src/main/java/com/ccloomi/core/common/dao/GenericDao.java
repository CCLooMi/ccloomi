package com.ccloomi.core.common.dao;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.util.StringUtil;

/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：GenericDao
 * 类 描 述：通用Dao，所有的Dao都应该继承此Dao
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月19日-下午5:28:48
 * @param <T>
 */
@Service("baseDao")
public class GenericDao<T extends BaseEntity> extends AbstractDao<T> implements BaseDao<T>{
	@Override
	public Object save(T entity){
		if(entity instanceof IdEntity){
			IdEntity idEntity=(IdEntity) entity;
			if(idEntity.getId()==null){
				idEntity.setId(StringUtil.buildUUID());
			}
			int i=add(entity);
			if(i>0){
				return idEntity.getId();
			}else{
				return null;
			}
		}else{
			return add(entity);
		}
	}
}
