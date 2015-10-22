package com.ccloomi.web.system.tag;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.jsp.JspException;

import org.springframework.beans.factory.annotation.Autowired;

import com.ccloomi.core.component.sql.imp.SQLMaker;
import com.ccloomi.core.tag.CCBootstrapInputSuportTag;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.system.entity.UserEntity;
import com.ccloomi.web.system.service.UserService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：CCUserSelectTag
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年9月24日-下午10:07:04
 */
public class CCUserSelectTag extends CCBootstrapInputSuportTag{
	private static final long serialVersionUID = 3491169928753504763L;
	@Autowired
	private UserService userService;
	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder sb=new StringBuilder();
		StringBuilder options=new StringBuilder();
		sb.append("<div class=\"form-group\">");
		SQLMaker sm=new SQLMaker();
		sm.SELECT("u.id,u.username")
		.FROM(new UserEntity(), "u");
		List<Map<String, Object>>list=userService.findBySQLGod(sm);
		for(Map<String, Object>m:list){
			Object userid=m.get("id");
			String select=userid.equals(this.value)?"selected = \"selected\"":"";
			options.append(StringUtil.format("<option value=\"?\" ?>?</option>", userid,select,m.get("username")));
		}
		sb.append(labelHTML());
		sb.append(cocoon(selectHTML(options.toString(), name, name)));
		sb.append("</div>");
		out.write(sb.toString());
	}

}
