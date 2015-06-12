package cn.vfunding.vfunding.plat.mq.receiver;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage;

@Component
public class SendVerifyCodeReceiver extends MessageReceiver {

	@Resource(name = "senderSmsRestInvokerFactory")
	private RestInvokerFactory senderSmsRestInvokerFactory;

	@Override
	public void receive(Object message) {
		SendVerifyCodeMessage ms = (SendVerifyCodeMessage) message;
		this.sendSms(ms);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.SendVerifyCodeMessage");
	}

	/**
	 * 发送短信方法
	 * 
	 * @param content
	 *            短信内容
	 * @param mobile
	 *            手机号码
	 * @author liuyijun 2014年4月9日
	 */
	@SuppressWarnings("unused")
	private void sendSms(String content, String mobile) {
		StringBuilder path = new StringBuilder("?");
		path.append("phone=" + mobile);
		path.append("&content=" + content);
		path.append("&type=systme&from=vfunding-www");
		this.senderSmsRestInvokerFactory.getRestInvoker().get(path.toString(),
				Integer.class);
	}

	private void sendSms(SendVerifyCodeMessage ms) {
		// StringBuilder path = new StringBuilder("?");
		// path.append("phone=" + mobile);
		// path.append("&content=" + content);
		// path.append("&type=systme&from=vfunding-www");
		Map<String, String> msg = new HashMap<String, String>();
		msg.put("phone", ms.getMobile());
		msg.put("content", ms.getContent());
		msg.put("type", "system");
		msg.put("from", "vfunding-www");
		this.senderSmsRestInvokerFactory.getRestInvoker().post("/byJson", msg);
	}

}
