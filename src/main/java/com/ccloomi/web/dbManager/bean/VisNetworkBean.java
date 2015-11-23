package com.ccloomi.web.dbManager.bean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.ccloomi.core.common.bean.BaseBean;

/**
 * 类    名：VisNetworkBean
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年11月23日-上午11:33:03
 */
public class VisNetworkBean extends BaseBean{
	private static final long serialVersionUID = -1761835641521029608L;
	private List<Object>nodes=new ArrayList<Object>();
	private List<Object>edges=new ArrayList<Object>();
	public VisNetworkBean addNodes(Collection<? extends Object>nodes){
		this.nodes.addAll(nodes);
		return this;
	}
	public VisNetworkBean addNodes(Object...nodes){
		for(Object node:nodes){
			this.nodes.add(node);
		}
		return this;
	}
	public VisNetworkBean addEdges(Collection<? extends Object>edges){
		this.edges.addAll(edges);
		return this;
	}
	public VisNetworkBean addEdges(Object...edges){
		for(Object edge:edges){
			this.edges.add(edge);
		}
		return this;
	}
	//gets and sets
	/**
	 * 获取：nodes
	 */
	public List<Object> getNodes() {
		return nodes;
	}
	/**
	 * 设置：nodes
	 */
	public void setNodes(List<Object> nodes) {
		this.nodes = nodes;
	}
	/**
	 * 获取：edges
	 */
	public List<Object> getEdges() {
		return edges;
	}
	/**
	 * 设置：edges
	 */
	public void setEdges(List<Object> edges) {
		this.edges = edges;
	}
}
