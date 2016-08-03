package com.sql.entity;


import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sql.annotation.Column;
import com.sql.annotation.ExtendType;
import com.sql.annotation.MappedSuperclass;
import com.sql.annotation.Table;
import com.sql.bean.BaseBean;
import com.sql.bean.ValuePair;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseEntity
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午8:43:46
 */
public abstract class BaseEntity extends BaseBean{
	private static final long serialVersionUID = -37893553063050791L;
	private boolean hasPrepareProperties=false;
	private List<String>propertiesA=new ArrayList<String>();
	private List<String>propertiesV=new ArrayList<String>();
	private Map<String, ValuePair> mapping=new LinkedHashMap<>();
	private Map<String, Object>extendTypeMap=new HashMap<>();
	//用于临时存储
	private Object extendType;
	/**获取 mapping*/
	public Map<String, ValuePair> getMapping() {
		return mapping;
	}
	/**获取 extendTypeMap*/
	public Map<String, Object> getExtendTypeMap() {
		return extendTypeMap;
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月26日 - 下午10:25:40
	 * @param name
	 * @return
	 */
	public Object getPropertyValue(Object name){
		return getObjectPropertyValue(this,(String)name);
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
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2016年8月2日 - 下午3:54:03
	 * @return
	 */
	public ValuePair getCommonValuePair(){
		ValuePair vp=mapping.get("common");
		if(vp==null){
			return new ValuePair();
		}
		return vp;
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2016年8月2日 - 下午3:54:06
	 * @param tableName
	 * @return
	 */
	public ValuePair getTableValuePair(String tableName){
		ValuePair vp=mapping.get(tableName);
		if(vp==null){
			return new ValuePair();
		}
		return vp;
	}
	/**
	 * 描述：
	 * 作者：Chenxj
	 * 日期：2015年8月26日 - 下午10:43:11
	 * @return
	 */
	public String tableName(){
		String tableName=getTableName(getClass());
		return tableName==null?getClass().getSimpleName():tableName;
	}
	private String getTableName(Class<?>c){
		Table t=c.getDeclaredAnnotation(Table.class);
		if(t!=null){
			return t.name();
		}
		return null;
	}
	/**
	 * 描述：用于缓存整个对象
	 * 作者：Chenxj
	 * 日期：2016年8月3日 - 下午10:29:12
	 * @return
	 */
	public Map<String, Object>PVMap(){
		Map<String,Object>map=new HashMap<>();
		for(String p:propertiesA){
			map.put(p, getPropertyValue(p));
		}
		return map;
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
	public void prepareProperties(){
		if(!hasPrepareProperties){
			this.findAllProperties(getClass());
			Collections.reverse(propertiesA);
			this.hasPrepareProperties=true;
		}
	}
	public boolean hasExtendType(){
		return this.classHasExtendTypeAnnotation(getClass());
	}
	private void findAllProperties(Class<?>c){
		Field[]fields=c.getDeclaredFields();
		int l=fields.length;
		String tableName=getTableName(c);
		if(tableName==null)tableName="common";
		ValuePair valuePair=mapping.get(tableName);
		if(valuePair==null){
			valuePair=new ValuePair();
			mapping.put(tableName, valuePair);
		}
		if(classHasExtendTypeAnnotation(c)){
			extendType=c.getDeclaredAnnotation(ExtendType.class).value();
		}
		for(Field f:fields){
			String pName=f.getName();
			String getMethodName="get"+pName.substring(0,1).toUpperCase()+pName.substring(1);
			
			Method getMethod=null;
			try{
				getMethod=c.getDeclaredMethod(getMethodName);
			}catch(Exception e){
				continue;
			}
			Column column=getMethod.getDeclaredAnnotation(Column.class);
			if(column==null){
				column=f.getDeclaredAnnotation(Column.class);
			}
			String columnName=column==null?pName:column.name();
			valuePair.addPair(pName, columnName);
			propertiesV.add(pName);
			propertiesA.add(fields[--l].getName());
			//处理extendType
			ExtendType et=getMethod.getDeclaredAnnotation(ExtendType.class);
			if(et==null){
				et=f.getDeclaredAnnotation(ExtendType.class);
			}
			if(et!=null){
				extendTypeMap.put(pName, extendType);
				extendTypeMap.put(columnName, extendType);
			}
		}
		if(superClassHasMappedSuperclassAnnotation(c)||superClassHasTableAnnotation(c)){
			findAllProperties(c.getSuperclass());
		}
	}
	private boolean superClassHasMappedSuperclassAnnotation(Class<?>c){
		return c.getSuperclass().getDeclaredAnnotation(MappedSuperclass.class)==null?false:true;
	}
	private boolean superClassHasTableAnnotation(Class<?>c){
		return c.getSuperclass().getDeclaredAnnotation(Table.class)==null?false:true;
	}
	private boolean classHasExtendTypeAnnotation(Class<?>c){
		return c.getDeclaredAnnotation(ExtendType.class)==null?false:true;
	}
	public static void main(String[] args) {
		SuperSellerEntity su=new SuperSellerEntity();
		su.prepareProperties();
		System.out.println("INFO::"+su.getExtendTypeMap());
		System.out.println("INFO::"+su.getMapping());
	}
}
