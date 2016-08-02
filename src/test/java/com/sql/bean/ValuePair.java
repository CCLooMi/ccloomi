package com.sql.bean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：ValuePair
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-上午10:05:21
 */
public class ValuePair extends BaseBean{
	private static final long serialVersionUID = -3482467309122297506L;
	private Map<Object, Object>m1=new HashMap<>();
	private Map<Object, Object>m2=new HashMap<>();
	public ValuePair addPair(Object v1,Object v2){
		m1.put(v1, v2);
		m2.put(v2, v1);
		return this;
	}
	@SuppressWarnings("unchecked")
	public <T> T get(Object v){
		Object o=m1.get(v);
		if(o==null){
			o=m2.get(v);
		}
		return (T)o;
	}
	public <T> T get(Object v,Class<T>clazz){
		return get(v);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getLeft(Object v){
		Object o=m1.get(v);
		if(o==null){
			o=v;
		}
		return (T)o;
	}
	public <T> T getLeft(Object v,Class<T>clazz){
		return getLeft(v);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T getRight(Object v){
		Object o=m2.get(v);
		if(o==null){
			o=v;
		}
		return (T)o;
	}
	public <T> T getRight(Object v,Class<T>clazz){
		return getRight(v);
	}
	public Set<Object> getLeftAll(){
		return m1.keySet();
	}
	public Set<Object> getRightAll(){
		return m2.keySet();
	}
	/**获取 m1*/
	public Map<Object, Object> getM1() {
		return m1;
	}
	/**获取 m2*/
	public Map<Object, Object> getM2() {
		return m2;
	}
	public static void main(String[] args) {
		ValuePair vp=new ValuePair();
		vp.addPair("abc", "123");
		String a=vp.get("abc");
		String b=vp.get("123");
		System.out.println(a);
		System.out.println(b);
		System.out.println(vp.get("123", String.class));
	}
}
