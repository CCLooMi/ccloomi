package com.ccloomi.core.component.binery;

import java.io.DataInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.ccloomi.core.component.binery.annotation.BytesLength;
import static com.ccloomi.core.util.BytesUtil.*;
import com.ccloomi.core.util.ClassUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseBineryReader
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月6日-下午9:35:30
 */
public class CCInputStream extends DataInputStream{
	
	public CCInputStream(InputStream in) {
		super(in);
	}
	public void read(CCStructure structure){
		Class<?>c=structure.getClass();
		Field[]fields=structure.getClass().getDeclaredFields();
		for(Field f:fields){
			String pName=f.getName();
			Method getMethod=null;
			Method setMethod=null;
			try{
				getMethod=ClassUtil.getMethod(c, pName);
				setMethod=ClassUtil.setMethod(c, pName);
				byte[]bytes=(byte[]) getMethod.invoke(structure);
				if(bytes==null){
					BytesLength bl=c.getDeclaredAnnotation(BytesLength.class);
					if(bl!=null){
						String refPName=bl.ref();
						Method refGetMethod=ClassUtil.getMethod(c, refPName);
						byte[]bytes2=(byte[]) refGetMethod.invoke(structure);
						int byteLength=readBytesToInt(bytes2,structure.endianness());
						byte[]b=new byte[byteLength];
						setMethod.invoke(structure, b);
					}
				}else{
					this.in.read(bytes);
				}
			}catch(Exception e){
				continue;
			}
		}
	}
}
