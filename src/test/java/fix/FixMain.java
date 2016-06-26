package fix;

import java.io.FileInputStream;

import quickfix.Acceptor;
import quickfix.DefaultMessageFactory;
import quickfix.FileLogFactory;
import quickfix.FileStoreFactory;
import quickfix.LogFactory;
import quickfix.MessageFactory;
import quickfix.MessageStoreFactory;
import quickfix.SessionSettings;
import quickfix.SocketAcceptor;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：FixMain
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月25日-下午1:36:32
 */
public class FixMain {
	public void test() throws Exception{
	    String fileName =getClass().getClassLoader().getResource("").getPath()+"fix/fixSettings2";
	    FixApp application = new CCFixAPP();
	    SessionSettings settings = new SessionSettings(new FileInputStream(fileName));
	    MessageStoreFactory storeFactory = new FileStoreFactory(settings);
	    LogFactory logFactory = new FileLogFactory(settings);
	    MessageFactory messageFactory = new DefaultMessageFactory();
	    Acceptor acceptor = new SocketAcceptor(application, storeFactory, settings, logFactory, messageFactory);
	    acceptor.start();
	    // while(condition == true) { do something; }
	    acceptor.stop();
	}
	public static void main(String[] args) throws Exception {
		new FixMain().test();
	}
}
