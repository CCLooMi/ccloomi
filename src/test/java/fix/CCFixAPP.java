package fix;

import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;

/**© 2015-2016 CCLooMi.Inc Copyright
 * 类    名：CCFixAPP
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年6月25日-下午1:37:32
 */
public class CCFixAPP implements FixApp{

	@Override
	public void onCreate(SessionID sessionId) {
		System.out.println("onCreate::"+sessionId);
	}

	@Override
	public void onLogon(SessionID sessionId) {
		System.out.println("onLogon::"+sessionId);
	}

	@Override
	public void onLogout(SessionID sessionId) {
		System.out.println("onLogout::"+sessionId);
	}

	@Override
	public void toAdmin(quickfix.Message message, SessionID sessionId) {
		System.out.println("toAdmin::"+sessionId);
	}

	@Override
	public void fromAdmin(quickfix.Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
		System.out.println("fromAdmin::"+sessionId);
	}

	@Override
	public void toApp(quickfix.Message message, SessionID sessionId) throws DoNotSend {
		System.out.println("toApp::"+sessionId);
	}

	@Override
	public void fromApp(quickfix.Message message, SessionID sessionId)
			throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
		System.out.println("fromApp::"+sessionId);
	}

}
