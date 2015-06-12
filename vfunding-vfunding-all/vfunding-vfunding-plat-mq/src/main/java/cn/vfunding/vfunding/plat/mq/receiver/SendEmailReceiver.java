package cn.vfunding.vfunding.plat.mq.receiver;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.common.module.activemq.message.SendEmailMessage;

@Component
public class SendEmailReceiver extends MessageReceiver {

	/**
	 * 邮件服务器
	 */
	@Resource(name = "senderEmailRestInvokerFactory")
	private RestInvokerFactory senderEmailRestInvokerFactory;

	@Override
	public void receive(Object message) {
		SendEmailMessage msg = (SendEmailMessage) message;
		this.sendVerificationEmail(msg.getEmail(), msg.getContent(), msg.getTitle());
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.SendEmailMessage");
	}

	public void sendVerificationEmail(String email, String content, String title) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", title);
		map.put("addressesString", email);
		map.put("content", content);
		// 发送邮件
		this.senderEmailRestInvokerFactory.getRestInvoker().post("", map);
	}

}
