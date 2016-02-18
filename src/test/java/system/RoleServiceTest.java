package system;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.system.service.RoleService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：RoleServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年11月1日-下午12:13:27
 */
public class RoleServiceTest extends BaseTest<RoleService>{

	@Override
	protected void test(RoleService testObj) {
//		String idUser="06f3c36bf1a74b35998215b999e4dd15";
//		System.out.println(testObj.findRolesByIdUser(idUser));
//		System.out.println(testObj.findViewsByIdUser(idUser));
//		System.out.println(testObj.findPermissionsByIdUser(idUser));
		System.out.println(testObj.findAll());
	}
	public static void main(String[] args) {
		new RoleServiceTest().runTest();
	}
}
