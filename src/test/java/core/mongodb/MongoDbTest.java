package core.mongodb;

import org.bson.Document;

import com.ccloomi.core.test.BaseTest;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：MongoDbTest
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年3月12日-上午8:36:16
 */
public class MongoDbTest extends BaseTest<MongoDatabase>{

	@Override
	protected void test(MongoDatabase testObj) {
		System.out.println(testObj.getName());
		MongoCollection<Document>userCollection=testObj.getCollection("user");
		userCollection.insertOne(new Document("name", "ccloomi"));
		FindIterable<Document>result=userCollection.find();
		MongoCursor<Document>mc=result.iterator();
		while(mc.hasNext()){
			System.out.println("查找结果：："+mc.next());
		}
	}
	public static void main(String[] args) {
		new MongoDbTest().runTest();
	}
}
