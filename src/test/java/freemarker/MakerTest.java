package freemarker;

import java.io.File;
import java.io.PrintWriter;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class MakerTest {
	public static void main(String[] args) throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_24);
		String path=MakerTest.class.getClassLoader().getResource("").getPath()+"freemarker";
		cfg.setDirectoryForTemplateLoading(new File(path));
		Template t=cfg.getTemplate("kml.ftl");
		KmlBean kml=new KmlBean();
		kml.initPlaceMarks();
		System.out.println("Model::"+kml);
		System.out.println("Model生成结果::");
		PrintWriter out=new PrintWriter(System.out);
		t.process(kml, out);
	}
}
