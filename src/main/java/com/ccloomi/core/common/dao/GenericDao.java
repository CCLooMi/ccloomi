package com.ccloomi.core.common.dao;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccloomi.core.common.entity.BaseEntity;
import com.ccloomi.core.common.entity.IdEntity;
import com.ccloomi.core.util.JSONUtil;
import com.ccloomi.core.util.StringUtil;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
//import static com.mongodb.client.model.Sorts.ascending;
/**
 * © 2015-2015 CCLooMi.Inc Copyright
 * 类    名：GenericDao
 * 类 描 述：通用Dao，所有的Dao都应该继承此Dao
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月19日-下午5:28:48
 * @param <T>
 */
@Service("baseDao")
public class GenericDao<T extends BaseEntity> extends AbstractDao<T> implements BaseDao<T>{
	@Autowired
	protected MongoDatabase mongoDatabase;
	/**
	 * 描述：自动设置ID
	 * 作者：Chenxj
	 * 日期：2016年1月24日 - 下午3:10:34
	 * @param entity
	 * @return
	 */
	@Override
	public Object save(T entity){
		if(entity instanceof IdEntity){
			IdEntity idEntity=(IdEntity) entity;
			if(idEntity.getId()==null){
				idEntity.setId(StringUtil.buildUUID());
			}
			int i=add(entity);
			if(i>0){
				return idEntity.getId();
			}else{
				return null;
			}
		}else{
			return add(entity);
		}
	}
	@Override
	public void saveOrUpdate(T entity){
		int i=update(entity);
		if(i!=1){
			save(entity);
		}
	}
	@Override
	public Map<String,Object> findAsMap(Object id) {
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		FindIterable<Document>rs=c.find(eq("_id", id));
		MongoCursor<Document>mc=rs.iterator();
		while(mc.hasNext()){
			Document d=mc.next();
			if(!d.isEmpty()){
				d.put("id", d.remove("_id"));
				return d;
			}else{//缓存中没有找到则从数据库里面查询
				T entity=getById(id);
				if(entity!=null){
					entity.prepareProperties();
					cacheMap(entity.PVMap());
					return entity.PVMap();
				}
			}
		}
		return new Document();
	}
	
	@Override
	public Map<String, Object> findAsMap(Map<String, Integer> columnFilterMap,Object id) {
		Document projection=new Document();
		projection.putAll(columnFilterMap);
		
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		FindIterable<Document>rs=c.find(eq("_id", id)).projection(projection);
		MongoCursor<Document>mc=rs.iterator();
		while(mc.hasNext()){
			Document d=mc.next();
			if(!d.isEmpty()){
				d.put("id", d.remove("_id"));
				return d;
			}else{//缓存中没有找到则从数据库里面查询
				T entity=getById(id);
				if(entity!=null){
					entity.prepareProperties();
					cacheMap(entity.PVMap());
					return entity.PVMap();
				}
			}
		}
		return new Document();
	}

	@Override
	public List<Map<String, Object>> findAsListMap(Collection<? extends Object> ids) {
		return findAsListMap(ids.toArray());
	}

	@Override
	public List<Map<String, Object>> findAsListMap(Map<String, Integer> columnFilterMap,Collection<? extends Object> ids) {
		return findAsListMap(columnFilterMap,ids.toArray());
	}
	
	@Override
	public <id>List<Map<String, Object>> findAsListMap(@SuppressWarnings("unchecked") id...ids) {
		List<Map<String, Object>>ld=new ArrayList<>();
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		FindIterable<Document>rc=c.find(in("_id", Arrays.asList(ids)));
		MongoCursor<Document>mc=rc.iterator();
		while(mc.hasNext()){
			Document d=mc.next();
			d.put("id", d.remove("_id"));
			ld.add(d);
		}
		//缓存中没有的需要从数据库里面查询
		if(ld.size()<ids.length){
			List<Object>misses=new ArrayList<>();
			for(Object id:ids){
				if(!ld.contains(id)){
					misses.add(id);
				}
			}
			List<T>entities=getByIds(misses);
			for(T entity:entities){
				entity.prepareProperties();
				ld.add(entity.PVMap());
			}
			cacheListEntity(entities);
		}
		return ld;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <id> List<Map<String, Object>> findAsListMap(Map<String, Integer> columnFilterMap, id... ids) {
		Document projection=new Document();
		projection.putAll(columnFilterMap);
		List<Map<String, Object>>ld=new ArrayList<>();
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		FindIterable<Document>rc=c.find(in("_id", ids)).projection(projection);
		MongoCursor<Document>mc=rc.iterator();
		while(mc.hasNext()){
			Document d=mc.next();
			d.put("id", d.remove("_id"));
			ld.add(d);
		}
		//缓存中没有的需要从数据库里面查询
		if(ld.size()<ids.length){
			List<Object>misses=new ArrayList<>();
			for(Object id:ids){
				if(!ld.contains(id)){
					misses.add(id);
				}
			}
			List<T>entities=getByIds(misses);
			for(T entity:entities){
				entity.prepareProperties();
				ld.add(entity.PVMap());
			}
			cacheListEntity(entities);
		}
		return ld;
	}
	
	@Override
	public T findAsEntity(Object id) {
		return JSONUtil.convertMapToBean(findAsMap(id), entityClass);
	}

	@Override
	public T findAsEntity(Map<String, Integer> columnFilterMap, Object id) {
		return JSONUtil.convertMapToBean(findAsMap(columnFilterMap,id), entityClass);
	}
	
	@Override
	public List<T> findAsListEntity(Collection<? extends Object> ids) {
		return findAsListEntity(ids.toArray());
	}

	@Override
	public List<T> findAsListEntity(Map<String, Integer> columnFilterMap,Collection<? extends Object> ids) {
		return findAsListEntity(columnFilterMap,ids.toArray());
	}
	
	@Override
	public <id>List<T> findAsListEntity(@SuppressWarnings("unchecked") id...ids) {
		List<T>ld=new ArrayList<>();
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		FindIterable<Document>rc=c.find(in("_id",ids));
		MongoCursor<Document>mc=rc.iterator();
		while(mc.hasNext()){
			Document d=mc.next();
			d.put("id", d.remove("_id"));
			ld.add(JSONUtil.convertMapToBean(d, entityClass));
		}
		//缓存中没有的需要从数据库里面查询
		if(ld.size()<ids.length){
			List<Object>misses=new ArrayList<>();
			for(Object id:ids){
				if(!ld.contains(id)){
					misses.add(id);
				}
			}
			List<T>entities=getByIds(misses);
			ld.addAll(entities);
			cacheListEntity(entities);
		}
		return ld;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <id> List<T> findAsListEntity(Map<String, Integer> columnFilterMap,id... ids) {
		Document projection=new Document();
		projection.putAll(columnFilterMap);
		List<T>ld=new ArrayList<>();
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		FindIterable<Document>rc=c.find(in("_id",ids)).projection(projection);
		MongoCursor<Document>mc=rc.iterator();
		while(mc.hasNext()){
			Document d=mc.next();
			d.put("id", d.remove("_id"));
			ld.add(JSONUtil.convertMapToBean(d, entityClass));
		}
		//缓存中没有的需要从数据库里面查询
		if(ld.size()<ids.length){
			List<Object>misses=new ArrayList<>();
			for(Object id:ids){
				if(!ld.contains(id)){
					misses.add(id);
				}
			}
			List<T>entities=getByIds(misses);
			ld.addAll(entities);
			cacheListEntity(entities);
		}
		return ld;
	}
	
	@Override
	public void cacheMap(Map<String, ? extends Object> map) {
		Map<String, Object>m=new HashMap<>();
		m.putAll(map);
		m.put("_id", m.remove("id"));
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		try{
			c.insertOne(new Document(m));
			int a=m.keySet().size();
			_entity.prepareProperties();
			int b=_entity.properties().size();
			if(a<b){
				T t=getById(map.get("id"));
				t.prepareProperties();
				t.PVMap().putAll(map);
				cacheMap(t.PVMap());
			}
		}catch(Exception e){
			c.updateOne(eq("_id", map.get("id")), new Document("$set", new Document(m)));
		}
	}

	@Override
	public void cacheListMap(List<Map<String, Object>> maps) {
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		List<Document>documents=new ArrayList<>();
		for(Map<String,Object>m:maps){
			Map<String, Object>mm=new HashMap<>();
			mm.putAll(m);
			mm.put("_id", mm.remove("id"));
			documents.add(new Document(mm));
		}
		try{
			c.insertMany(documents);
		}catch(Exception e){
			for(Map<String, Object>m:maps){
				cacheMap(m);
			}
		}
	}

	@Override
	public void cacheEntity(T entity) {
		entity.prepareProperties();
		cacheMap(entity.PVMap());
	}
	@Override
	public void cacheListEntity(List<T> entities) {
		List<Map<String, Object>>maps=new ArrayList<>();
		for(T entity:entities){
			entity.prepareProperties();
			maps.add(entity.PVMap());
		}
		cacheListMap(maps);
	}
	@Override
	public void uncache(Object id) {
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		c.deleteOne(eq("_id",id));
	}
	@Override
	public void uncacheList(Collection<? extends Object> ids) {
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		c.deleteMany(in("_id",ids));
	}
	@Override
	public <id>void uncacheList(@SuppressWarnings("unchecked") id...ids) {
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		List<Object>lds=new ArrayList<Object>();
		for(Object id:ids){
			lds.add(id);
		}
		c.deleteMany(in("_id",lds));
	}
	
	@Override
	public void recacheMap(Map<String, ? extends Object> map) {
		MongoCollection<Document>c=mongoDatabase.getCollection(tableName);
		Map<String,Object>m=new HashMap<String, Object>();
		m.putAll(map);
		m.put("_id", m.remove("id"));
		c.replaceOne(eq("_id",map.get("id")), new Document(m));
	}
	@Override
	public void recacheListMap(List<Map<String, Object>> maps) {
		for(Map<String, Object>map:maps){
			recacheMap(map);
		}
	}
	@Override
	public void recacheEntity(T entity) {
		if(entity!=null){
			entity.prepareProperties();
			recacheMap(entity.PVMap());
		}
	}
	@Override
	public void recacheListEntity(List<T> entities) {
		for(T entity:entities){
			recacheEntity(entity);
		}
	}
}
