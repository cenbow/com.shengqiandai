package cn.vfunding.vfunding.biz.jmssender.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.kaptcha.VerifyCodeUtils;

@Component
public class VerifyCodeService {

	@Autowired
	private JmsSenderService senderService;

	public String getVerifyCode(String mobile, String smsKey,
			HttpSession httpSession) throws Exception {
		String verifyCode = VerifyCodeUtils.createMobileVerifyCode(6,
				httpSession);
		String[] args = new String[] { verifyCode };
		senderService.sendSms(mobile, smsKey, args);
		return verifyCode;
	}

}
