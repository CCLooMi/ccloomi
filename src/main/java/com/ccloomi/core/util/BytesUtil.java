package com.ccloomi.core.util;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BytesUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月25日-上午11:39:12
 */
public class BytesUtil {
	/**
	 * 描述：字节数组转整形
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:26:02
	 * @param bytes 字节数组
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static int readBytesToInt(byte[]bytes,int endianness){
		int a=0;
		int length=bytes.length;
		if(endianness==1){
			for(int i=length-1;i>-1;i--){
				a|=(bytes[i]&0xFF)<<(i*8);
			}
		}else if(endianness==-1){
			for(int i=0;i<length;i++){
				a|=(bytes[i]&0xFF)<<((length-1-i)*8);
			}
		}
		return a;
	}
	/**
	 * 描述：整形转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:25:22
	 * @param a 整形
	 * @param length 字节数组长度
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static byte[] intToBytes(int a,int length,int endianness){
		byte[]b=new byte[length];
		if(endianness==1){
			for(int i=length-1;i>-1;i--){
				b[i]= (byte) (a>>(8*i)&0xFF);
			}
		}else if(endianness==-1){
			for(int i=0;i<length;i++){
				b[i]= (byte) (a>>(8*(length-1-i))&0xFF);
			}
		}
		return b;
	}
	/**
	 * 描述：字节数组转长整形
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:31:49
	 * @param bytes
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static long readBytesToLong(byte[]bytes,int endianness){
		long a=0;
		int length=bytes.length;
		if(endianness==1){
			for(int i=length-1;i>-1;i--){
				a|=((long)bytes[i]&0xFF)<<(i*8);
			}
		}else if(endianness==-1){
			for(int i=0;i<length;i++){
				a|=((long)bytes[i]&0xFF)<<((length-1-i)*8);
			}
		}
		return a;
	}
	/**
	 * 描述：长整形转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:32:18
	 * @param a 长整形
	 * @param length 字节数组长度
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static byte[] longToBytes(long a,int length,int endianness){
		byte[]b=new byte[length];
		if(endianness==1){
			for(int i=length-1;i>-1;i--){
				b[i]= (byte) (a>>(8*i)&0xFF);
			}
		}else if(endianness==-1){
			for(int i=0;i<length;i++){
				b[i]= (byte) (a>>((length-1-i)*8)&0xFF);
			}
		}
		return b;
	}
	/**
	 * 描述：字节数组转双精度类型
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:40:16
	 * @param bytes 字节数组
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static double readBytesToDouble(byte[]bytes,int endianness){
		return Double.longBitsToDouble(readBytesToLong(bytes,endianness));
	}
	/**
	 * 描述：双精度类型转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:42:16
	 * @param a 双精度类型
	 * @param length 字节数组长度
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static byte[] doubleToBytes(double a,int length,int endianness){
		return longToBytes(Double.doubleToLongBits(a), length,endianness);
	}
	/**
	 * 描述：字节数组转Float
	 * 作者：Chenxj
	 * 日期：2016年6月25日 - 下午12:30:53
	 * @param bytes
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static float readBytesToFloat(byte[]bytes,int endianness){
		int a=0;
		int length=bytes.length;
		if(endianness==1){
			for(int i=length-1;i>-1;i--){
				a|=((long)bytes[i]&0xFF)<<(i*8);
			}
		}else if(endianness==-1){
			for(int i=0;i<length;i++){
				a|=((long)bytes[i]&0xFF)<<((length-1-i)*8);
			}
		}
		return Float.intBitsToFloat(a);
	}
	/**
	 * 描述：Float转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月25日 - 下午12:31:24
	 * @param a Float
	 * @param length 字节数组长度
	 * @param endianness 字节序( 1:Big Endian,-1:Little Endian)
	 * @return
	 */
	public static byte[] floatToBytes(float a,int length,int endianness){
		long b=Float.floatToIntBits(a);
		return longToBytes(b, length,endianness);
	}
}
