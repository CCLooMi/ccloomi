package com.sql.test;

import com.sql.Maker;
import com.sql.SQLMaker;
import com.sql.entity.SuperSellerEntity;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MakerTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年8月2日-下午3:59:32
 */
public class MakerTest {
	public static void main(String[] args) {
		SQLMaker sm=new SQLMaker() {
			@Override
			public Maker LIMIT(int page, int pageSize) {
				this.limit=" LIMIT ?,?";
				this.values.add(page*pageSize);
				this.values.add(pageSize);
				return this;
			}
		};
		sm.SELECT("ss.id,ss.address")
		.FROM(new SuperSellerEntity(), "ss")
		.WHERE("ss.id=?", "123")
		.AND("ss.address=?", "aaa");
		long l=System.currentTimeMillis();
		System.out.println(sm);
		System.out.println("TIMES::"+(System.currentTimeMillis()-l)+"ms");
	}
}
