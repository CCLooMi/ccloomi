package com.ccloomi.web.system.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.core.tag.CCBootstrapInputSuportTag;
import com.ccloomi.core.tag.InputEnum;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.system.entity.DataDictionaryEntity;
import com.ccloomi.web.system.service.DataDictionaryService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CCInputTag
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月22日-下午11:03:18
 */
public class CCInputTag extends CCBootstrapInputSuportTag{
	private static final long serialVersionUID = -1165095592558752576L;
	@Autowired
	private DataDictionaryService dataDictionaryService;
	private String key;
	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder sb=new StringBuilder();
		sb.append("<div class=\"form-group\">");
		if(type==InputEnum.text||type==InputEnum.password){
			sb.append(labelHTML(name));
			sb.append(inputHTML(type,name,name,value,label));
		}else if(type==InputEnum.textarea){
			sb.append(labelHTML(name));
			sb.append(textareaHTML(name,name,label,value));
		}else if(type==InputEnum.select){
			
		}else if(type==InputEnum.radio){
			StringBuilder radios=new StringBuilder();
			SQLMaker sm=new SQLMaker();
			sm.SELECT("dd.id,dd.name,dd.V,dd.pid,dd.desc")
			.FROM(new DataDictionaryEntity(), "dd")
			.WHERE("dd.K=?", key);
			List<Map<String, Object>>list=dataDictionaryService.findBySQLGod(sm);
			for(Map<String, Object>m:list){
				Object pid=m.get("pid");
				Object v=m.get("V");
				Object dName=m.get("name");
				String desc=(String) m.get("desc");
				if(pid==null||"".equals(pid)){
					label=(String)dName;
				}else{
					desc=(desc==null?"":"（"+desc+"）");
					radios.append(radioHTML(name, String.valueOf(v), v,dName+desc));
				}
			}
			sb.append(labelHTML());
			sb.append(cocoon(radios.toString()));
		}else if(type==InputEnum.checkbox){
			
		}else if(type==InputEnum.submit){
			//name为必填项，由于button不需要name属性故将name属性用来存储类名
			String n="btn "+name;
			sb.append(cocoon(StringUtil.format("<button type=\"submit\" class=\"?\">?</button>", n,value)));
		}
		sb.append("</div>");
		out.write(sb.toString());
	}
	/**获取 key*/
	public String getKey() {
		return key;
	}
	/**设置 key*/
	public void setKey(String key) {
		this.key = key;
	}
}
