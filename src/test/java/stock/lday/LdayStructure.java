package stock.lday;

import com.ccloomi.core.component.binery.CCStructure;
import com.ccloomi.core.component.binery.annotation.BytesType;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：LdayStructure
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月25日-下午12:02:19
 */
public class LdayStructure extends CCStructure{
	@BytesType(propertyType="int")
	private byte[]date=new byte[4];
	@BytesType(propertyType="int")
	private byte[]open=new byte[4];
	@BytesType(propertyType="int")
	private byte[]hight=new byte[4];
	@BytesType(propertyType="int")
	private byte[]low=new byte[4];
	@BytesType(propertyType="int")
	private byte[]close=new byte[4];
	@BytesType(propertyType="float")
	private byte[]vom=new byte[4];
	@BytesType(propertyType="int")
	private byte[]vol=new byte[4];
	@BytesType(propertyType="int")
	private byte[]refClose=new byte[4];
	/**获取 date*/
	public byte[] getDate() {
		return date;
	}
	/**设置 date*/
	public void setDate(byte[] date) {
		this.date = date;
	}
	/**获取 open*/
	public byte[] getOpen() {
		return open;
	}
	/**设置 open*/
	public void setOpen(byte[] open) {
		this.open = open;
	}
	/**获取 hight*/
	public byte[] getHight() {
		return hight;
	}
	/**设置 hight*/
	public void setHight(byte[] hight) {
		this.hight = hight;
	}
	/**获取 low*/
	public byte[] getLow() {
		return low;
	}
	/**设置 low*/
	public void setLow(byte[] low) {
		this.low = low;
	}
	/**获取 close*/
	public byte[] getClose() {
		return close;
	}
	/**设置 close*/
	public void setClose(byte[] close) {
		this.close = close;
	}
	/**获取 vom*/
	public byte[] getVom() {
		return vom;
	}
	/**设置 vom*/
	public void setVom(byte[] vom) {
		this.vom = vom;
	}
	/**获取 vol*/
	public byte[] getVol() {
		return vol;
	}
	/**设置 vol*/
	public void setVol(byte[] vol) {
		this.vol = vol;
	}
	/**获取 refClose*/
	public byte[] getRefClose() {
		return refClose;
	}
	/**设置 refClose*/
	public void setRefClose(byte[] refClose) {
		this.refClose = refClose;
	}
}
