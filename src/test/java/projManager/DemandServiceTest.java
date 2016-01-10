package projManager;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.projManager.entity.DemandEntity;
import com.ccloomi.web.projManager.service.DemandService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：DemandServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年1月10日-下午10:32:57
 */
public class DemandServiceTest extends BaseTest<DemandService>{

	@Override
	protected void test(DemandService testObj) {
		DemandEntity entity=new DemandEntity();
		entity.setTitle("Test demand.");
		System.out.println(testObj.save(entity));
	}
	public static void main(String[] args) {
		new DemandServiceTest().runTest();
	}
}
