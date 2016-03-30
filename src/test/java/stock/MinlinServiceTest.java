package stock;

import java.io.File;
import java.io.FilenameFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	private Logger log=LoggerFactory.getLogger(getClass());
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
		int i=0;
		int totol=files.length;
		float rate=0;
		for(File f:files){
			testObj.importMinlineInfoFromMinlineFile(f);
			rate=++i/totol;
			log.debug("数据导入进度：[{}]", rate);
		}
	}
	public static void main(String[] args) {
		new MinlinServiceTest().runTest();
	}
}
