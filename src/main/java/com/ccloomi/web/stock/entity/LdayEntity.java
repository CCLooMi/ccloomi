package com.ccloomi.web.stock.entity;

import java.util.Date;

import javax.persistence.Table;

import com.ccloomi.core.common.entity.IdEntity;
/**
 * 类名：LdayEntity
 * 描述：
 * 作者： Chenxj
 * 日期：2016年3月29日 - 下午1:07:29
 */
@Table(name="t_s_lday")
public class LdayEntity extends IdEntity{
	private static final long serialVersionUID = 521764798572583119L;
	private String idStock;
	/**日期*/
	private Date date;
	/**开盘价*/
	private Float open;
	/**最高价*/
	private Float high;
	/**最低价*/
	private Float low;
	/**收盘价*/
	private Float close;
	/**成交量*/
	private String trading;
	/**成交额*/
	private double turnover;
	public String getIdStock() {
		return idStock;
	}
	public void setIdStock(String idStock) {
		this.idStock = idStock;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Float getOpen() {
		return open;
	}
	public void setOpen(Float open) {
		this.open = open;
	}
	public Float getHigh() {
		return high;
	}
	public void setHigh(Float high) {
		this.high = high;
	}
	public Float getLow() {
		return low;
	}
	public void setLow(Float low) {
		this.low = low;
	}
	public Float getClose() {
		return close;
	}
	public void setClose(Float close) {
		this.close = close;
	}
	public String getTrading() {
		return trading;
	}
	public void setTrading(String trading) {
		this.trading = trading;
	}
	public double getTurnover() {
		return turnover;
	}
	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}
}
