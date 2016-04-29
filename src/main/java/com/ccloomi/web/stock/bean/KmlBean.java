package com.ccloomi.web.stock.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.bean.BaseBean;
import com.ccloomi.core.util.StringUtil;

import static com.ccloomi.core.util.MapUtil.*;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：KmlBean
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月29日-下午9:47:50
 */
public class KmlBean extends BaseBean{
	private static final long serialVersionUID = -2447392793631782923L;
	private List<Map<String, String>>placemarks;
	public KmlBean(){
		placemarks=new ArrayList<>();
	}
	public void add(String name,String discription,String longitude,String latitude){
		placemarks.add(stringMap("name",name,"open",0,"discription",discription,"longitude",longitude,"latitude",latitude));
	}
	public void addByMapList(List<Map<String, Object>>maps){
		for(Map<String, Object>m:maps){
			String discription="<html><body>公司::?<br>地址::?<br>主营::?<br>发行价::?<br></body></html>";
			
			placemarks.add(stringMap("name",m.get("brand_name"),
					"open",0,
					"description",StringUtil.format(discription, m.get("name"),m.get("address"),m.get("description"),m.get("IssuePrice")),
					"longitude",m.get("longitude"),
					"latitude",m.get("latitude")));
		}
	}
	/**获取 placemarks*/
	public List<Map<String, String>> getPlacemarks() {
		return placemarks;
	}
	/**设置 placemarks*/
	public void setPlacemarks(List<Map<String, String>> placemarks) {
		this.placemarks = placemarks;
	}
}
