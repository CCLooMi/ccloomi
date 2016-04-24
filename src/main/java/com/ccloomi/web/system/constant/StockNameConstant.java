package com.ccloomi.web.system.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.web.stock.entity.StockEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StockNameConstant
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月24日-上午7:53:35
 */
public class StockNameConstant extends TableConstant{
	public static Map<String, String>getStockNameConstantMap(){
		return getConstantMap(StockNameConstant.class.getName(),new DoSelect() {
			
			@Override
			public void doSelect(SQLMaker sm) {
				sm.SELECT("s.id")
				.SELECT("s.name")
				.FROM(new StockEntity(), "s");
			}
			
			@SuppressWarnings("unchecked")
			@Override
			public <T> Map<String, T> convertToMap(List<Map<String, Object>> lsMap) {
				Map<String, String>m = new HashMap<>();
				for(Map<String, Object>mm:lsMap){
					m.put(String.valueOf(mm.get("id")), String.valueOf(mm.get("name")));
				}
				return (Map<String, T>) m;
			}
		});
	}
	public static void main(String[] args) {
		System.out.println(StockNameConstant.getStockNameConstantMap());
	}
}
