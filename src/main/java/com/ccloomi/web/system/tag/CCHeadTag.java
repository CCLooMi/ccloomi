package com.ccloomi.web.system.tag;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;

import com.ccloomi.core.tag.BaseBodyTag;
import com.ccloomi.core.util.StringUtil;

public class CCHeadTag extends BaseBodyTag{
	private static final long serialVersionUID = 4475655796155693258L;
	private String linkTemplate="<link rel=\"stylesheet\" href=\"?\">";
	private String scriptTemplate="<script src=\"?\"></script>";
	private String pageName;
	private String title;
	
	@Override
	protected void startTag() throws Exception {
		StringBuilder sb=new StringBuilder();
		sb.append(StringUtil.format("<base href=\"?\">", basePath()));
		sb.append("<meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">");
		sb.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">");
		sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">");
		sb.append(StringUtil.format("<title>?</title>", title==null?pageName():title));
		sb.append(StringUtil.format(linkTemplate, "res/css/bootstrap.css"));
		sb.append(StringUtil.format(linkTemplate, "res/css/bootstrap-theme.css"));
		out.write(sb.toString());
	}
	@Override
	protected void afterBodyTag() throws Exception {
		String body=bodyContent.getString();
		Set<String>cssSet=new HashSet<String>();
		Set<String>jsSet=new HashSet<String>();
		//匹配去掉左边和右边空格内容的字符
		Pattern pattern=Pattern.compile("[^\\s].+\\..+[^\\s]");
		Matcher matcher=pattern.matcher(body);
		while(matcher.find()){
			if(matcher.group().contains(".css"))
				cssSet.add(matcher.group());
			else if(matcher.group().contains(".js"))
				jsSet.add(matcher.group());
		}
		
		StringBuilder sb=new StringBuilder();
		for(String css:cssSet){
			sb.append(StringUtil.format(linkTemplate, "res/"+css));
		}
		//自定义的CSS/JS要在所有CSS/JS文件之后
		sb.append(between());
		for(String js:jsSet){
			sb.append(StringUtil.format(scriptTemplate, "res/"+js));
		}
		out.write(sb.toString());
	}
	@Override
	protected void endTag() throws Exception {
		StringBuilder sb=new StringBuilder();
		//如果标签体内没有内容则需要加上中间的CSS/JS文件引用
		if(bodyContent==null){
			sb.append(between());
		}
		sb.append(StringUtil.format(scriptTemplate, "res/js/common.js"));
		sb.append(StringUtil.format(scriptTemplate, "res/js/view/"+pageName()+".js"));
		out.write(sb.toString());
	}
	/**
	 * 描述：自定义的CSS/JS要在所有CSS/JS文件之后,故有此方法
	 * 作者：Chenxj
	 * 日期：2015年9月27日 - 下午12:09:57
	 * @return
	 */
	private StringBuilder between(){
		StringBuilder sb=new StringBuilder();
		sb.append(StringUtil.format(linkTemplate, "res/css/view/"+pageName()+".css"));
		sb.append(StringUtil.format(scriptTemplate, "res/js/jquery-2.1.4.min.js"));
		sb.append(StringUtil.format(scriptTemplate, "res/js/bootstrap.js"));
		return sb;
	}
	private String basePath(){
		ServletContext context=this.pageContext.getServletContext();
		ServletRequest request=this.pageContext.getRequest();
		String path = context.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		return basePath;
	}
	/**
	 * 描述：获取当前标签所在JSP的文件名(不带.JSP后缀)
	 * 作者：Chenxj
	 * 日期：2015年9月27日 - 下午12:13:21
	 * @return
	 */
	private String pageName(){
		if(pageName==null){
			Pattern pattern=Pattern.compile("(?!=\\.)[^\\.]+(?=_jsp)");
			Matcher matcher=pattern.matcher(this.pageContext.getPage().getClass().toString());
			while(matcher.find()){
				pageName=matcher.group();
				break;
			}
		}
		return pageName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
