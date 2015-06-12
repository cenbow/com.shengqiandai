package cn.vfunding.vfunding.plat.mq.receiver;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.common.module.activemq.message.SendVerifyEmailMessage;
@Component
public class SendVerifyEmailReceiver extends MessageReceiver {

	/**
	 * 邮件服务器
	 */
	@Resource(name = "senderEmailRestInvokerFactory")
	private RestInvokerFactory senderEmailRestInvokerFactory;
	
	@Override
	public void receive(Object message) {
		SendVerifyEmailMessage msg=(SendVerifyEmailMessage) message;
        this.sendVerificationEmail(msg.getEmail(), msg.getUrl(), msg.getUserName());
	}
	
	@PostConstruct
	public void setMessageInfo(){
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.SendVerifyEmailMessage");
	}
	
	
	public void sendVerificationEmail(String email, String url, String userName) {
		StringBuilder sb = new StringBuilder("亲爱的  <span style=\"color:red\">");
		sb.append(userName);
		sb.append("</span>, 您好！<p>");
		sb.append("感谢您注册微积金，请确认您的邮箱帐号为 :" + email + "</p>");
		sb.append("<p>请点击下面的链接即可完成激活：</p>");
		sb.append("<p>" + url + "</p>");
		sb.append("<p>&nbsp;&nbsp;(如果链接无法点击，请将它拷贝到浏览器的地址栏中)</p>");
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "微积金注册确认邮件");
		map.put("addressesString", email);
		map.put("content", sb.toString());
		// 发送邮件
		this.senderEmailRestInvokerFactory.getRestInvoker().post("", map);
	}

}
