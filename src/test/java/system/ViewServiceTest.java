package system;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.system.service.ViewService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		try {
			System.out.println(new ObjectMapper().writeValueAsString(testObj.findViewsTreeByRoleId("4564gfsdg4r64gf64dy84rs64f4gs64f")));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new ViewServiceTest().runTest();
	}
}
