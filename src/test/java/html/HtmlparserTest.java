package html;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.HtmlPage;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：HtmlparserTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月4日-下午5:52:31
 */
public class HtmlparserTest {
	public static void main(String[] args)throws Exception {
		Parser parser=new Parser("http://basic.10jqka.com.cn/mobile/600435/profilen.html");
		Parser templateParser =new Parser(HtmlparserTest.class.getClassLoader().getResource("").getPath()+"html/template.html");
		parser.setEncoding("GBK");
		HtmlPage html=new HtmlPage(parser);
		HtmlPage html_t=new HtmlPage(templateParser);
		parser.visitAllNodesWith(html);
		templateParser.visitAllNodesWith(html_t);
		System.out.println(html.getBody());
		NodeList nodeList=html_t.getBody();
		NodeList result=new NodeList();
		HtmlparserTest.lookAll(nodeList, result);
		System.out.println(result);
	}
	public static NodeList lookAll(NodeList nodeList,NodeList result){
		for(int i=0;i<nodeList.size();i++){
			Node node=nodeList.elementAt(i);
			NodeList cls=node.getChildren();
			if(cls!=null){
				if(cls.size()==1){
					Node cnode=cls.elementAt(0);
					String t=cnode.getText();
					if(t!=null&&t.contains("{")){
						result.add(cnode);
					}
				}else{
					lookAll(cls,result);
				}
			}
		}
		return result;
	}
}
