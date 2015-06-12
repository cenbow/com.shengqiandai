package cn.vfunding.common.framework.send.util;

public class SendCommonUtil {
	public static SendCommon createSmsSendCommon(String phone, String key,
			String[] args) {
		SendCommon send = new SendCommon(key, args);
		send.setPhone(phone);
		return send;
	}

	public static SendCommon createEmailSendCommon(String email, String key,
			String[] args) {
		SendCommon send = new SendCommon(key, args);
		send.setType("email");
		send.setEmail(email);
		return send;
	}
}