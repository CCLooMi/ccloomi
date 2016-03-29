package stock;

import java.io.File;
import java.io.FilenameFilter;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.stock.service.MinlineService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MinlinServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月29日-下午9:08:34
 */
public class MinlinServiceTest extends BaseTest<MinlineService>{

	@Override
	protected void test(MinlineService testObj) {
		File file=new File("/Volumes/Macintosh/Documents/stock/gupM");
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
			testObj.importMinlineInfoFromMinlineFile(f);
			break;
		}
	}
	public static void main(String[] args) {
		new MinlinServiceTest().runTest();
	}
}
