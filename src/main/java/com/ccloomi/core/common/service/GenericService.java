package com.ccloomi.core.common.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.ccloomi.core.common.entity.IdEntity;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：GenericService
 * 类 描 述：通用Service，所有Service都应该继承此Service
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月22日-下午9:59:08
 * @param <T>
 */
@Service("baseService")
@Transactional
public class GenericService<T extends IdEntity> extends AbstractService<T> implements BaseService<T>{
	
}
