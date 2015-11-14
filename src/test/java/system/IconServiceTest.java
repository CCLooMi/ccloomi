package system;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.system.entity.IconEntity;
import com.ccloomi.web.system.service.IconService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：IconServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月14日-下午8:26:30
 */
public class IconServiceTest extends BaseTest<IconService>{
	
	@Override
	protected void test(IconService testObj) {
		List<IconEntity>ls=new ArrayList<IconEntity>();
		try{
			InputStream in=ClassLoader.class.getResourceAsStream("/icons.txt");
			BufferedReader br=new BufferedReader(new InputStreamReader(in));
			for(String line=br.readLine();line!=null;line=br.readLine()){
				IconEntity icon=new IconEntity();
				String[]s=line.split(" ");
				icon.setId(StringUtil.buildUUID());
				icon.setGroupName(s[0]);
				icon.setClassName(s[1]);
				ls.add(icon);
			}
			System.out.println(ls);
			System.out.println(testObj.batchSave(ls).length);
		}catch(Exception e){
			
		}
	}
	public static void main(String[] args) {
		new IconServiceTest().runTest();
	}
}
