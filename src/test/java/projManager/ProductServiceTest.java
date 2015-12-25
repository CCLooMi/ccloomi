package projManager;

import java.util.HashMap;
import java.util.Map;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.projManager.service.ProductService;

/**© 2015-2015 CCLooMi.Inc Copyright
 * 类    名：ProductServiceTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2015年12月25日-下午10:53:26
 */
public class ProductServiceTest extends BaseTest<ProductService>{

	@Override
	protected void test(ProductService testObj) {
		Map<String, Object>map=new HashMap<>();
		map.put("pageNumber", 1);
		map.put("pageSize", 10);
		System.out.println(testObj.findByPage(map));
	}
	public static void main(String[] args) {
		new ProductServiceTest().runTest();
	}
}
