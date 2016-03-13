package system;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.system.dao.UserDao;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：UserDaoTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月13日-下午12:31:51
 */
public class UserDaoTest extends BaseTest<UserDao>{

	@Override
	protected void test(UserDao testObj) {
		Map<String, Object>map=new HashMap<>();
		map.put("id", "1111");
		map.put("username", "apple1");
		Map<String, Object>map2=new HashMap<>();
		map2.put("id", "1122");
		map2.put("username", "apple2");
		List<Map<String, Object>>listMap=new ArrayList<>();
		listMap.add(map);
		listMap.add(map2);
//		testObj.cacheListMap(listMap);
//		System.out.println(testObj.findAsListDocument("1111","1122"));
//		System.out.println(testObj.findAsListEntity("1111","1122"));
		System.out.println(testObj.findAsMap("110").isEmpty());
	}
	public static void main(String[] args) {
		new UserDaoTest().runTest();
	}
}
