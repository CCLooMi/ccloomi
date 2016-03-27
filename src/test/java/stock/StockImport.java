package stock;

import java.io.File;

import com.ccloomi.core.test.BaseTest;
import com.ccloomi.web.stock.service.StockService;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：StockTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月27日-上午11:05:54
 */
public class StockImport extends BaseTest<StockService>{
	public static void main(String[] args) {
		new StockImport().runTest();
	}
	
	@Override
	protected void test(StockService testObj) {
		File excelFile=new File("/Users/chenxianjun/Desktop/沪深Ａ股20160325.xlsx");
		testObj.importStockInfoFromFile(excelFile);
	}
}
