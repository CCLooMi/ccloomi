package stock;

import java.io.File;
import java.io.FilenameFilter;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.stock.service.LdayService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：LdayServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月31日-上午7:05:23
 */
public class LdayServiceTest extends BaseTest<LdayService>{

	@Override
	protected void test(LdayService testObj) {
		File file=new File("/Volumes/Macintosh/Documents/stock/gupD");
		File[]files=file.listFiles(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				if(name.contains("#")){
					return true;
				}else{
					return false;
				}
			}
		});
		for(File f:files){
			testObj.importLdayInfoFromFile(f);
		}
	}
	public static void main(String[] args) {
		new LdayServiceTest().runTest();
	}
}
