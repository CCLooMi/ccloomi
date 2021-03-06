package com.ccloomi.core.component.sms.yx;

import com.ccloomi.core.component.sms.SmsCodeUtil;
/**
 * © 2015-2016 CCLooMi.Inc Copyright
 * 类    名：YxSmsCodeUtil
 * 类 描 述：
 * 作    者：Chenxj
 * 邮    箱：chenios@foxmail.com
 * 日    期：2016年7月30日-下午6:40:12
 */
public class YxSmsCodeUtil extends SmsCodeUtil{
	static{
		map.put("000000","ok");
		map.put("100000","金额不为整数");
		map.put("100001","余额不足");
		map.put("100002","数字非法");
		map.put("100003","不允许有空值");
		map.put("100004","枚举类型取值错误");
		map.put("100005","访问IP不合法");
		map.put("100006","手机号不合法");
		map.put("100015","号码不合法");
		map.put("100500","HTTP状态码不等于200");
		map.put("100007","查无数据");
		map.put("100008","手机号码为空");
		map.put("100009","手机号为受保护的号码");
		map.put("100010","登录邮箱或手机号为空");
		map.put("100011","邮箱不合法");
		map.put("100012","密码不能为空");
		map.put("100013","没有测试子账号");
		map.put("100014","金额过大,不要超过12位数字");
		map.put("100016","余额被冻结");
		map.put("100017","余额已注销");
		map.put("100018","通话时长需大于60秒");
		map.put("100699","系统内部错误");
		map.put("100019","应用餘額不足");
		map.put("100020","字符长度太长");
		map.put("100104","callId不能为空");
		map.put("100105","日期格式错误");
		map.put("100108","取消语音操作失败");
		map.put("101100","请求包头Authorization参数为空");
		map.put("101101","请求包头Authorization参数Base64解码失败");
		map.put("101102","请求包头Authorization参数解码后账户ID为空");
		map.put("101103","请求包头Authorization参数解码后时间戳为空");
		map.put("101104","请求包头Authorization参数解码后格式有误");
		map.put("101105","主账户ID存在非法字符");
		map.put("101106","请求包头Authorization参数解码后时间戳过期");
		map.put("101107","请求地址SoftVersion参数有误");
		map.put("101108","主账户已关闭");
		map.put("101109","主账户未激活");
		map.put("101110","主账户已锁定");
		map.put("101111","主账户不存在");
		map.put("101112","主账户ID为空");
		map.put("101113","请求包头Authorization参数中账户ID跟请求地址中的账户ID不一致");
		map.put("101114","请求地址Sig参数为空");
		map.put("101115","请求token校验失败");
		map.put("101116","主账号sig加密串不匹配");
		map.put("101117","主账号token不存在");
		map.put("102100","应用ID为空");
		map.put("102101","应用ID存在非法字符");
		map.put("102102","应用不存在");
		map.put("102103","应用未审核通过");
		map.put("102104","测试应用不允许创建client");
		map.put("102105","应用不属于该主账号");
		map.put("102106","应用类型错误");
		map.put("102107","应用类型为空");
		map.put("102108","应用名为空");
		map.put("102109","行业类型为空");
		map.put("102110","行业信息错误");
		map.put("102111","是否允许拨打国际填写错误");
		map.put("102112","是否允许拨打国际不能为空");
		map.put("102113","创建应用失败");
		map.put("102114","应用名称已存在");
		map.put("103100","子账户昵称为空");
		map.put("103101","子账户名称存在非法字符");
		map.put("103102","子账户昵称长度有误");
		map.put("103103","子账户clientNumber为空");
		map.put("103104","同一应用下，friendlyname重复");
		map.put("103105","子账户friendlyname只能包含数字和字母和下划线");
		map.put("103106","client_number长度有误");
		map.put("103107","client_number不存在或不属于该主账号");
		map.put("103108","client已经关闭");
		map.put("103109","client充值失败");
		map.put("103110","client计费类型为空");
		map.put("103111","clientType只能取值0,1");
		map.put("103112","clientType为1时，charge不能为空");
		map.put("103113","clientNumber未绑定手机号");
		map.put("103114","同一应用下同一手机号只能绑定一次");
		map.put("103115","单次查询记录数不能超过100");
		map.put("103116","绑定手机号失败");
		map.put("103117","子账号是否显示数字(isplay)不能为空");
		map.put("103118","子账号是否显示数字(display)取值只能是0(不显示数字)和1(显示数字)");
		map.put("103119","应用下该子账号不存在");
		map.put("103120","friendlyname不能为空");
		map.put("103121","查询client参数不能为空");
		map.put("103122","client不属于应用");
		map.put("103123","未上线应用不能超过100个client");
		map.put("103124","已经是开通状态");
		map.put("103125","子账号余额不足");
		map.put("103126","未上线应用或demo只能使用白名单中号码");
		map.put("103127","测试demo不能创建子账号");
		map.put("103128","校验码不能为空");
		map.put("103129","校验码错误或失效");
		map.put("103130","校验号码失败");
		map.put("103131","解绑失败,信息错误或不存在绑定关系");
		map.put("104100","主叫clientNumber为空");
		map.put("104101","主叫clientNumber未绑定手机号");
		map.put("104102","验证码为空");
		map.put("104103","显示号码不合法");
		map.put("104104","语音验证码位4-8位");
		map.put("104105","语音验证码位4-8位");
		map.put("104106","语音通知类型错误");
		map.put("104107","语音通知内容为空");
		map.put("104108","语音ID非法");
		map.put("104109","文本内容存储失败");
		map.put("104110","语音文件不存在或未审核");
		map.put("104111","号码与绑定的号码不一致");
		map.put("104112","开通或关闭呼转失败");
		map.put("104113","不能同时呼叫同一被叫");
		map.put("104114","内容包含敏感词");
		map.put("104115","语音通知发送多语音ID不能超过5个");
		map.put("104116","不在线呼转模式只能取1,2,3,4");
		map.put("104117","呼转模式为2,4则必须填写forwardPhone");
		map.put("104118","呼转模式为2,4则前转号码与绑定手机号码不能相等");
		map.put("104119","群聊列表格式不合法");
		map.put("104120","群聊呼叫模式参数错误");
		map.put("104121","群聊ID不能为空");
		map.put("104122","群聊超过最大方数");
		map.put("104123","群聊ID发送错误");
		map.put("104124","群聊操作失败服务出错");
		map.put("104125","呼转号码不存在");
		map.put("104126","订单号不能为空");
		map.put("104127","订单号不存在");
		map.put("104128","号码释放失败或号码已经自动释放");
		map.put("104129","显手机号必须是呼叫列表中的号码");
		map.put("104130","主被叫不能相同");
		map.put("104132","callid不能为空");
		map.put("104133","发起者不能为空");
		map.put("105100","短信服务请求异常");
		map.put("105101","url关键参数为空");
		map.put("105102","号码不合法");
		map.put("105103","没有通道类别");
		map.put("105104","该类别为冻结状态");
		map.put("105105","没有足够金额");
		map.put("105106","不是国内手机号码并且不是国际电话");
		map.put("105107","黑名单");
		map.put("105108","含非法关键字");
		map.put("105109","该通道类型没有第三方通道");
		map.put("105110","短信模板ID不存在");
		map.put("105111","短信模板未审核通过");
		map.put("105112","短信模板替换个数与实际参数个数不匹配");
		map.put("105113","短信模板ID为空");
		map.put("105114","短信内容为空");
		map.put("105115","短信类型长度应为1");
		map.put("105116","同一天同一用户不能发超过3条相同的短信");
		map.put("105117","模板ID含非法字符");
		map.put("105118","短信模板有替换内容，但参数为空");
		map.put("105119","短信模板替换内容过长，不能超过70个字符");
		map.put("105120","手机号码不能超过100个");
		map.put("105121","短信模板已删除");
		map.put("105122","同一天同一用户不能发超过10条验证码(因运营商限制一般情况下不足5条)");
		map.put("105123","短信模板名称为空");
		map.put("105124","短信模板内容为空");
		map.put("105125","创建短信模板失败");
		map.put("105126","短信模板名称错误");
		map.put("105127","短信模板内容错误");
		map.put("105128","短信模板id为空");
		map.put("105129","短信模板id不存在");
		map.put("103123","未上线应用不能超过100个client");
		map.put("103124","已经是开通状态");
		map.put("103125","子账号余额不足");
		map.put("103126","未上线应用或demo只能使用白名单中号码");
		map.put("103127","测试demo不能创建子账号");
		map.put("105130","30秒内不能连续发同样的内容");
		map.put("105131","30秒内不能给同一号码发送相同模板消息");
		map.put("105132","验证码短信参数长度不能超过10位");
		map.put("200001","应用id为空");
		map.put("200002","应用不存在");
		map.put("200003","应用未审核通过");
		map.put("200004","应用不属于该主账户");
		map.put("200005","主账户id为空");
		map.put("200006","主账户不存在");
		map.put("200007","主账户未激活");
		map.put("200008","主账户权限不足");
		map.put("200009","时间戳为空");
		map.put("200010","时间戳格式错误，格式为：yyyyMMddHHmmssSSS");
		map.put("200011","时间戳过期，过期时间为30分钟");
		map.put("200012","验证信息为空");
		map.put("200013","验证信息错误");
		map.put("200014","请勿重复提交，【主账户id+应用id+时间戳】不能重复");
		map.put("200099","系统内部错误");
	}
	public static String getSmsCodeDesc(String code){
		return map.get(code);
	}
}
