package com.ccloomi.core.component.binery;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.ccloomi.core.component.binery.annotation.BytesLength;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：CCStructure
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月14日-下午10:12:07
 */
public abstract class CCStructure {
	
	protected void read(CCInputStream in){
		Class<?>c=getClass();
		Field[]fields=getClass().getDeclaredFields();
		for(Field f:fields){
			String pName=f.getName();
			String mName=pName.substring(0,1).toUpperCase()+pName.substring(1);
			String getMethodName="get"+mName;
			String setMethodName="set"+mName;
			Method getMethod=null;
			Method setMethod=null;
			try{
				getMethod=c.getDeclaredMethod(getMethodName);
				setMethod=c.getDeclaredMethod(setMethodName);
				byte[]bytes=(byte[]) getMethod.invoke(this);
				if(bytes==null){
					BytesLength bl=c.getDeclaredAnnotation(BytesLength.class);
					if(bl!=null){
						//TODO
					}
				}
			}catch(Exception e){
				continue;
			}
		}
	}
}
