package com.ccloomi.core.component.binery;

import static com.ccloomi.core.util.BytesUtil.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import com.ccloomi.core.component.binery.annotation.BytesType;
import com.ccloomi.core.component.binery.annotation.SuperStructure;
import com.ccloomi.core.util.ClassUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：CCStructure
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月14日-下午10:12:07
 */
public abstract class CCStructure {
	private Map<String, Object>pvMap=new HashMap<>();
	public Map<String, Object>toMap(){
		if(pvMap.isEmpty()){
			findAllProperties(getClass());
		}
		return pvMap;
	}
	public Map<String, Object> newestMap(){
		findAllProperties(getClass());
		return pvMap;
	}
	private void findAllProperties(Class<?>c){
		Field[]fields=c.getDeclaredFields();
		for(Field f:fields){
			String pname=f.getName();
			Method getMethod=ClassUtil.getMethod(c, pname);
			if(getMethod!=null){
				try {
					Object value=getMethod.invoke(this);
					if(value instanceof byte[]){
						BytesType bt=f.getDeclaredAnnotation(BytesType.class);
						if(bt!=null){
							String ptype=bt.propertyType();
							if("int".equalsIgnoreCase(ptype)){
								pvMap.put(pname, readBytesToInt((byte[])value));
							}else if("float".equalsIgnoreCase(ptype)){
								pvMap.put(pname, readBytesToFloat((byte[])value));
							}else if("double".equalsIgnoreCase(ptype)){
								pvMap.put(pname, readBytesToDouble((byte[])value));
							}else if("long".equalsIgnoreCase(ptype)){
								pvMap.put(pname, readBytesToLong((byte[])value));
							}else if("string".equalsIgnoreCase(ptype)){
								pvMap.put(pname, new String((byte[])value, Charset.forName(bt.charset())));
							}else{
								pvMap.put(pname, value);
							}
						}else{
							//非byte[]类型
							pvMap.put(pname, value);
						}
					}else{
						//不带BytesType
						pvMap.put(pname, value);
					}
				} catch (Exception e) {
					continue;
				}
			}else{
				//无get方法
				pvMap.put(pname, null);
			}
		}
		if(superClassHasSuperStructureAnnotation(c)){
			findAllProperties(c.getSuperclass());
		}
	}
	private boolean superClassHasSuperStructureAnnotation(Class<?>c){
		return c.getSuperclass().getDeclaredAnnotation(SuperStructure.class)==null?false:true;
	}
}
