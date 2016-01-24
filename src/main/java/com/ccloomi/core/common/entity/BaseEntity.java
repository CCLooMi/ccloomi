package com.ccloomi.core.common.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;

import com.ccloomi.core.common.bean.BaseBean;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：BaseEntity
 * 类 描 述：抽象基础实体
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年7月15日-下午8:24:32
 */
public abstract class BaseEntity extends BaseBean{
	private static final long serialVersionUID = 4195520472702749062L;
	private boolean hasPrepareProperties=false;
	private List<String>propertiesA=new ArrayList<String>();
	private List<String>propertiesV=new ArrayList<String>();
	private Map<String, String>propertiesMap=new HashMap<String, String>();
	private Map<String, String>propertiesTableColumnsMap=new HashMap<String, String>();
	private Map<String, Object>PVMap=new HashMap<String, Object>();
	
	public void prepareProperties(){
		if(!hasPrepareProperties){
			findAllProperties(getClass());
			Collections.reverse(propertiesA);
			this.hasPrepareProperties=true;
		}
	};
	public void cleanProperties(){
		this.hasPrepareProperties=false;
		this.propertiesA=new ArrayList<String>();
		this.propertiesV=new ArrayList<String>();
		this.propertiesMap=new HashMap<String, String>();
		this.propertiesTableColumnsMap=new HashMap<String, String>();
		this.PVMap=new HashMap<String, Object>();
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月26日 - 下午10:43:11
	 * @return
	 */
	public String tableName(){
		Table t=getClass().getDeclaredAnnotation(Table.class);
		return t==null?getClass().getSimpleName():t.name();
	}
	/**
	 * 方法描述：通过名称查找
	 * 作者：Chenxj
	 * 日期：2015年7月24日 - 下午2:58:00
	 * @param name
	 * @return
	 */
	public String getProperty(String name){
		if(propertiesMap.containsKey(name)){
			return propertiesMap.get(name);
		}else{
			return name;
		}
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月26日 - 下午10:24:41
	 * @param name
	 * @return
	 */
	public String getPropertyTableColumn(String name){
		if(propertiesTableColumnsMap.containsKey(name)){
			return propertiesTableColumnsMap.get(name);
		}else{
			return name;
		}
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月26日 - 下午10:25:40
	 * @param name
	 * @return
	 */
	public Object getPropertyValue(String name){
		if(PVMap.containsKey(name)){
			return PVMap.get(name);
		}else{
			return null;
		}
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年10月22日 - 下午9:33:25
	 * @return
	 */
	public Map<String, Object>PVMap(){
		return PVMap;
	}
	/**
	 * 方法描述：从最顶层开始查找
	 * 作者：Chenxj
	 * 日期：2015年7月24日 - 下午2:57:03
	 * @param index
	 * @return
	 */
	public String getPropertyA(int index){
		return propertiesA.get(index);
	}
	/**
	 * 方法描述：从本实体开始向上查找
	 * 作者：Chenxj
	 * 日期：2015年7月24日 - 下午2:57:08
	 * @param index
	 * @return
	 */
	public String getPropertyV(int index){
		return propertiesV.get(index);
	}
	/**
	 * 方法描述：属性列表
	 * 作者：Chenxj
	 * 日期：2015年9月11日 - 上午10:13:16
	 * @return
	 */
	public List<String> properties(){
		return this.propertiesA;
	}
	private void findAllProperties(Class<?>c){
		Field[]fields=c.getDeclaredFields();
		int l=fields.length;

		for(Field f:fields){
			String pName=f.getName();
			String getMethodName="get"+pName.substring(0,1).toUpperCase()+pName.substring(1);
			
			Method getMethod=null;
			try{
				getMethod=c.getDeclaredMethod(getMethodName);
				PVMap.put(pName, getMethod.invoke(this));
			}catch(Exception e){
				continue;
			}
			Column column=getMethod.getDeclaredAnnotation(Column.class);
			if(column==null){
				column=f.getDeclaredAnnotation(Column.class);
			}
			String columnName=column==null?pName:column.name();
			propertiesV.add(pName);
			propertiesA.add(fields[--l].getName());
			propertiesMap.put(pName,pName);
			propertiesTableColumnsMap.put(pName, columnName);
		}
		if(superClassHasMappedSuperclassAnnotation(c)){
			findAllProperties(c.getSuperclass());
		}
	}
	
	private boolean superClassHasMappedSuperclassAnnotation(Class<?>c){
		return c.getSuperclass().getDeclaredAnnotation(MappedSuperclass.class)==null?false:true;
	}
}
