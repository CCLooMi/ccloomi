package dbManager;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.dbManager.service.ColumnsService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ColumnsTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月22日-下午4:18:18
 */
public class ColumnsServiceTest extends BaseTest<ColumnsService>{
	
	@Override
	protected void test(ColumnsService testObj) {
		System.out.println(testObj.findColumnsByTableSchemaAndTableName("ccloomi", "sys_user"));
	}
	public static void main(String[] args) {
		new ColumnsServiceTest().runTest();
	}
}
