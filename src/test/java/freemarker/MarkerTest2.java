package freemarker;

import java.io.PrintWriter;

import com.ccloomi.web.system.freemarker.DBTemplateLoader;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MarkerTest2
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年4月29日-下午10:32:22
 */
public class MarkerTest2 {
	public static void main(String[] args) throws Exception{
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
		cfg.setTemplateLoader(new DBTemplateLoader());
		Template t=cfg.getTemplate("google-kml");
		KmlBean kml=new KmlBean();
		kml.initPlaceMarks();
		System.out.println("Model::"+kml);
		System.out.println("Model生成结果::");
		PrintWriter out=new PrintWriter(System.out);
		t.process(kml, out);
	}
}
