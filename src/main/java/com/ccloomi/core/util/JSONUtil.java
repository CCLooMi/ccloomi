package com.ccloomi.core.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

import javax.persistence.Column;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ccloomi.core.common.entity.BaseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：JsonUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月24日-下午2:42:02
 */
public class JSONUtil {
	private static Logger log=LoggerFactory.getLogger(JSONUtil.class);
	private static ObjectMapper objMapper=new ObjectMapper();
	public static <T>T convertMapToBean(Map<String, ? extends Object>map,Class<T>elementType){
		try {
			T ebj=elementType.newInstance();
			if(ebj instanceof BaseEntity){
				BaseEntity b=(BaseEntity) ebj;
				b.prepareProperties();
				for(String p:b.properties()){
					Field field=getClassField(elementType, p);
					field.setAccessible(true);
					Object value=map.get(b.getPropertyTableColumn(p));
					if(value==null){value=map.get(p);}
					field.set(b, objMapper.convertValue(value, field.getType()));
				}
			}else{
				for(Field field:elementType.getDeclaredFields()){
					String columnName=field.getName();
					String get="get"+StringUtil.upperCaseFirstLatter(columnName);
					Method getMethod=elementType.getDeclaredMethod(get);
					Column c=getMethod.getDeclaredAnnotation(Column.class);
					if(c!=null){
						columnName=c.name();
					}
					if(map.get(columnName)!=null){
						field.setAccessible(true);
						field.set(ebj, objMapper.convertValue(map.get(columnName), field.getType()));
					}
				}
			}
			return ebj;
		}catch(Exception e){
			log.error("转换出现异常::", e);
		}
		return null;
	}
	private static Field getClassField(Class<?>clazz,String fieldName){
		Field field=null;
		try {
			field=clazz.getDeclaredField(fieldName);
		} catch (NoSuchFieldException e) {
			field=getClassField(clazz.getSuperclass(), fieldName);
		} catch (SecurityException e) {
			log.error("获取Field异常", e);
		}
		return field;
	}
}
