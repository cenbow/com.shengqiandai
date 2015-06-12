package cn.vfunding.common.framework.utils.verification;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;

/**
 * @author lilei 2014年4月9日
 */
public class VerificationUtils {

	@Resource(name = "senderSmsRestInvokerFactory")
	private RestInvokerFactory senderSmsRestInvokerFactory;

	/**
	 * 注册验证码，注册时获取手机验证码 及发送验证短信
	 * 
	 * @param mobile
	 *            手机号码
	 * @param httpSession
	 * @return
	 * @throws Exception
	 * @author liuyijun 2014年4月9日
	 */
	public String getVerifyCode(String mobile, String beforeContent,
			String afterContent, HttpSession httpSession) throws Exception {
		String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
				httpSession);
		StringBuilder content = new StringBuilder();
		content.append(beforeContent);
		content.append(verifyCode);
		content.append(afterContent);
		sendSms(content.toString(), mobile);
		return verifyCode;
	}

	public void sendSms(String content, String mobile) {
		StringBuilder path = new StringBuilder("?");
		path.append("phone=" + mobile);
		path.append("&content=" + content);
		path.append("&type=systme&from=p2p-www");
		this.senderSmsRestInvokerFactory.getRestInvoker().get(path.toString(),
				Integer.class);
	}

}
