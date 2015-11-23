package dbManager;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.dbManager.service.SchemataService;

/**
 * 类    名：SchemataServiceTest
 * 类描述：
 * 作    者：Chenxj
 * 日    期：2015年11月23日-下午2:34:25
 */
public class SchemataServiceTest extends BaseTest<SchemataService>{

	@Override
	protected void test(SchemataService testObj) {
		System.out.println(testObj.findAsVisNetworkBySchemaName("mysql"));
	}
	
	public static void main(String[] args) {
		new SchemataServiceTest().runTest();
	}
}
