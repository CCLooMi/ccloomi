package com.ccloomi.core.common.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
/**
 * 类名：IdEntity
 * 描述：基础实体抽象类
 * 作者： Chenxj
 * 日期：2015年6月23日 - 下午4:14:55
 */
@MappedSuperclass
public class IdEntity extends BaseEntity implements Serializable{
	private static final long serialVersionUID = 2917793786341780408L;
	private String id;

	@Id
	@GeneratedValue(generator = "paymentableGenerator")
	@GenericGenerator(name = "paymentableGenerator", strategy = "uuid")
	@Column(name ="id",nullable=false,length=32)
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
