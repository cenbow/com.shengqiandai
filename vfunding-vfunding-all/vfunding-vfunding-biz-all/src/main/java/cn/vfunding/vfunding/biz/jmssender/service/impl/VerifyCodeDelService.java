package cn.vfunding.vfunding.biz.jmssender.service.impl;

import javax.servlet.http.HttpSession;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class VerifyCodeDelService {
	
	@Async
	public void removePhoneVerifyCode(String desPhone,HttpSession session) {
		System.out.println("async start");
		try {
			Thread.sleep(300000);
		} catch (InterruptedException ex) {
			ex.printStackTrace();
			Thread.interrupted();
		}
		session.removeAttribute(desPhone);
		System.out.println("async end");
	}

}
