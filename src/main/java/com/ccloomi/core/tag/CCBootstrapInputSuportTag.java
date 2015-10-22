package com.ccloomi.core.tag;

import com.ccloomi.core.util.StringUtil;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CCBootstrapSuport
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月24日-下午10:29:56
 */
public abstract class CCBootstrapInputSuportTag extends BaseTag{
	private static final long serialVersionUID = -7464046010786500507L;
	protected InputEnum type=InputEnum.text;
	protected String name;
	protected String value="";
	protected String label;
	protected String labelHTML(){
		return label==null?"":StringUtil.format("<label class=\"col-sm-2 control-label\">?</label>",label);
	}
	protected String labelHTML(String forStr){
		return label==null?"":StringUtil.format("<label for=\"?\" class=\"col-sm-2 control-label\">?</label>", forStr,label);
	}
	protected String inputHTML(InputEnum type,String id,String name,Object value,String placeholder){
		return cocoon(StringUtil.format("<input type=\"?\" class=\"form-control\" id=\"?\" name=\"?\" value=\"?\" placeholder=\"?\">",type,name,name,value,placeholder));
	}
	protected String textareaHTML(String id,String name,String placeholder,Object value){
		return cocoon(StringUtil.format("<textarea class=\"form-control\" rows=\"3\" id=\"?\" name=\"?\" placeholder=\"?\">?</textarea>", name,name,placeholder,value));
	}
	protected String radioHTML(String name,String id,Object value,String label){
		//没有值则默认选中第一个
		if(this.value==null||"".equals(this.value))this.value=(String) value;
		String select=value.equals(this.value)?"checked=checked":"";
		return StringUtil.format("<div class=\"radio\"><label><input type=\"radio\" name=\"?\" id=\"?\" value=\"?\" ?>?</label></div>", name,id,value,select,label);
	}
	protected String selectHTML(String options,String id,String name){
		StringBuilder sb=new StringBuilder();
		sb.append(StringUtil.format("<select class=\"form-control\" id=\"?\" name=\"?\">", id,name));
		sb.append(options);
		sb.append("</select>");
		return sb.toString();
	}
	/**
	 * 描述：用DIV包裹
	 * 作者：Chenxj
	 * 日期：2015年9月24日 - 下午10:53:05
	 * @param toCocoon
	 * @return
	 */
	protected String cocoon(String toCocoon){
		StringBuilder sb=new StringBuilder();
		if(label!=null){
			sb.append("<div class=\"col-sm-10\">");
		}else{
			sb.append("<div class=\"col-sm-offset-2 col-sm-10\">");
		}
		sb.append(toCocoon);
		sb.append("</div>");
		return sb.toString();
	}
	/**获取 name*/
	public String getName() {
		return name;
	}
	/**设置 name*/
	public void setName(String name) {
		this.name = name;
	}
	/**获取 value*/
	public String getValue() {
		return value;
	}
	/**设置 value*/
	public void setValue(String value) {
		this.value = value;
	}
	/**获取 type*/
	public InputEnum getType() {
		return type;
	}
	/**设置 type*/
	public void setType(InputEnum type) {
		this.type = type;
	}
	/**获取 label*/
	public String getLabel() {
		return label;
	}
	/**设置 label*/
	public void setLabel(String label) {
		this.label = label;
	}
}
