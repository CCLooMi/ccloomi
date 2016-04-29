package freemarker;

import static com.ccloomi.core.util.MapUtil.stringMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.common.bean.BaseBean;

public class KmlBean extends BaseBean{
	private static final long serialVersionUID = -7708508779015522951L;
	private List<Map<String, String>>placemarks;
	
	public void initPlaceMarks(){
		placemarks=new ArrayList<>();
		for(int i=0;i<10;i++){
			placemarks.add(stringMap("name",i,
					"open",0,
					"description","<html>This is "+i+"</html>",
					"longitude",i*2,
					"latitude",i*7));
		}
	}
	public List<Map<String, String>> getPlacemarks() {
		return placemarks;
	}

	public void setPlacemarks(List<Map<String, String>> placemarks) {
		this.placemarks = placemarks;
	}
}
