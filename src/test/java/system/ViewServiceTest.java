package system;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.system.service.ViewService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ViewServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月10日-下午10:09:41
 */
public class ViewServiceTest extends BaseTest<ViewService>{

	@Override
	protected void test(ViewService testObj) {
		System.out.println(testObj.findViewsByRoleId("4564gfsdg4r64gf64dy84rs64f4gs64f"));
	}
	public static void main(String[] args) {
		new ViewServiceTest().runTest();
	}
}
