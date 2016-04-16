package html;

import com.ccloomi.core.util.TemplateParserUtil;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：TemplateParserTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月5日-下午10:36:13
 */
public class TemplateParserTest {
	public static void main(String[] args) {
		String template="<body><span>{name}</span><span>{age}</span></body>";
		String html="<body id='12312313'><div></div><span>jack</span><span>25</span></body>";
		System.out.println(TemplateParserUtil.parserHtmlTemplate2Map(html, template));
	}
}
