package com.ccloomi.web.system.constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.component.sql.SQLMaker;
import com.ccloomi.core.component.sql.SQLMakerFactory;
import com.ccloomi.core.util.SpringContextUtil;
import com.ccloomi.web.stock.dao.TagDao;
import com.ccloomi.web.stock.entity.TagEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TagConstant
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-下午3:40:18
 */
public class TagConstant {
	private static TagDao tagDao=SpringContextUtil.getBean("tagDao", TagDao.class);
	private static Map<String, Map<String, String>>map=new HashMap<>();
	/**
	 * 描述：股票ID对应板块的map
	 * 作者：Chenxj
	 * 日期：2016年4月24日 - 上午7:49:33
	 * @return
	 */
	public static Map<String, String> getId2NameMap(){
		return getTagConstantMap("stock_id2name");
	}
	public static Map<String, String> getName2IdMap(){
		return getTagConstantMap("stock_name2id");
	}
	private static Map<String, String>stockTagMap(String tagType){
		Map<String, String>mp=new HashMap<>();
		SQLMaker sm=SQLMakerFactory.getInstance().createMapker();
		sm.SELECT("*")
		.FROM(new TagEntity(), "t");
		List<TagEntity>tags=tagDao.findBySQLGod(sm,TagEntity.class);
		Map<String, String>id2name=new HashMap<>();
		Map<String, String>name2id=new HashMap<>();
		for(TagEntity tag:tags){
			id2name.put(tag.getId(), tag.getName());
			name2id.put(tag.getName(), tag.getId());
		}
		map.put("stock_id2name", id2name);
		map.put("stock_name2id", name2id);
		if("stock_id2name".equals(tagType)){
			return id2name;
		}else if("stock_name2id".equals(tagType)){
			return name2id;
		}
		return mp;
	}
	private synchronized static Map<String, String> getTagConstantMap(String tagType){
		if(!map.containsKey(tagType)){
			return stockTagMap(tagType);
		}
		return map.get(tagType);
	}
}
