package system;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.core.util.StringUtil;
import com.ccloomi.web.system.entity.UserEntity;
import com.ccloomi.web.system.service.UserService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：UserServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年10月31日-下午9:31:02
 */
public class UserServiceTest extends BaseTest<UserService>{

	@Override
	protected void test(UserService testObj) {
		UserEntity user=new UserEntity();
		user.setId(StringUtil.buildUUID());
		user.setNickname("Seemie");
		user.setPassword("apple");
		user.setUsername("Chenxj");
		testObj.save(user);
	}
	public static void main(String[] args) {
		new UserServiceTest().runTest();
	}
}
