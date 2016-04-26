package xml;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLMaker {
	public static void main(String[] args) {
		Document document=DocumentHelper.createDocument();
		Element kml=document.addElement("kml");
		kml.addAttribute("xmlns", "http://www.opengis.net/kml/2.2");
		kml.addAttribute("xmlns:gx", "http://www.google.com/kml/ext/2.2");
		kml.addAttribute("xmlns:kml", "http://www.opengis.net/kml/2.2");
		kml.addAttribute("xmlns:atom", "http://www.w3.org/2005/Atom");
		
		Element doc=kml.addElement("Document");
		doc.addElement("name").addText("KmlFile");
		doc.addElement("Style");
		System.out.println(document.asXML());
	}
}
