package stock.lday;

import java.io.File;
import java.io.FileInputStream;

import com.ccloomi.core.component.binery.CCInputStream;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：LadyReadTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月25日-下午12:06:32
 */
public class LadyReadTest {
	public static void main(String[] args) throws Exception {
		File f=new File("sz000001.day");
		CCInputStream cin=new CCInputStream(new FileInputStream(f));
		LdayStructure lday=new LdayStructure();
		cin.read(lday);
		System.out.println(lday.toMap());
		cin.read(lday);
		System.out.println(lday.newestMap());
		cin.close();
	}
}
