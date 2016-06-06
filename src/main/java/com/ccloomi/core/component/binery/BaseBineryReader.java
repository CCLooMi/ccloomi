package com.ccloomi.core.component.binery;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BaseBineryReader
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月6日-下午9:35:30
 */
public abstract class BaseBineryReader {
	/**
	 * 描述：字节数组转整形
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:26:02
	 * @param bytes 字节数组
	 * @return
	 */
	protected int readBytesToInt(byte[]bytes){
		int a=0;
		int length=bytes.length;
		for(int i=length-1;i>-1;i--){
			a|=(bytes[i]&0xFF)<<(i*8);
		}
		return a;
	}
	/**
	 * 描述：整形转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:25:22
	 * @param a 整形
	 * @param length 字节数组长度
	 * @return
	 */
	protected byte[] intToBytes(int a,int length){
		byte[]b=new byte[length];
		for(int i=length-1;i>-1;i--){
			b[i]= (byte) (a>>(8*i)&0xFF);
		}
		return b;
	}
	/**
	 * 描述：字节数组转长整形
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:31:49
	 * @param bytes
	 * @return
	 */
	protected long readBytesToLong(byte[]bytes){
		long a=0;
		int length=bytes.length;
		for(int i=length-1;i>-1;i--){
			a|=((long)bytes[i]&0xFF)<<(i*8);
		}
		return a;
	}
	/**
	 * 描述：长整形转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:32:18
	 * @param a 长整形
	 * @param length 字节数组长度
	 * @return
	 */
	protected byte[] longToBytes(long a,int length){
		byte[]b=new byte[length];
		for(int i=length-1;i>-1;i--){
			b[i]= (byte) (a>>(8*i)&0xFF);
		}
		return b;
	}
	/**
	 * 描述：字节数组转双精度类型
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:40:16
	 * @param bytes 字节数组
	 * @return
	 */
	protected double readBytesToDouble(byte[]bytes){
		return Double.longBitsToDouble(readBytesToLong(bytes));
	}
	/**
	 * 描述：双精度类型转字节数组
	 * 作者：Chenxj
	 * 日期：2016年6月6日 - 下午10:42:16
	 * @param a 双精度类型
	 * @param length 字节数组长度
	 * @return
	 */
	protected byte[] doubleToBytes(double a,int length){
		return longToBytes(Double.doubleToLongBits(a), length);
	}
}
