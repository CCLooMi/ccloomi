package dbManager;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.dbManager.service.TablesService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：TablesServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午4:19:51
 */
public class TablesServiceTest extends BaseTest<TablesService>{

	@Override
	protected void test(TablesService testObj) {
		System.out.println(testObj.findTablesByTableSchema("ccloomi"));
	}
	
	public static void main(String[] args) {
		new TablesServiceTest().runTest();
	}
}
