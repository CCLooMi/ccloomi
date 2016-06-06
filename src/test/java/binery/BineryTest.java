package binery;

import java.io.File;
import java.io.FileInputStream;

import com.ccloomi.core.component.binery.BaseBineryReader;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：BineryTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月6日-下午10:56:19
 */
public class BineryTest extends BaseBineryReader{
	public static void main(String[] args) throws Exception {
		File f=new File("/Users/chenxianjun/Desktop/preview.jpg");
		FileInputStream fin=new FileInputStream(f);
		byte[]b=new byte[2];
		fin.read(b);
		System.out.println(Integer.toHexString(new BineryTest().readBytesToInt(b)));
		fin.close();
	}
}
