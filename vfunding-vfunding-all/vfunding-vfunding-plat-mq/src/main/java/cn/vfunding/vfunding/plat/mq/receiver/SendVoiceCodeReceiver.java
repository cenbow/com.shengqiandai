package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.common.module.activemq.message.SendVoiceCodeMessage;
@Component
public class SendVoiceCodeReceiver extends MessageReceiver {

	@Resource(name = "senderVoiceRestInvokerFactory")
	private RestInvokerFactory senderVoiceRestInvokerFactory;
	@Override
	public void receive(Object message) {
		SendVoiceCodeMessage msg=(SendVoiceCodeMessage) message;
        this.sendVoice(msg.getVerifyCode(), msg.getMobile());
	}
	
	
	@PostConstruct
	public void setMessageInfo(){
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.SendVoiceCodeMessage");
	}
	
	/**
	 * 发送语音验证
	 * @param content
	 * @param mobile
	 * 2014年5月15日
	 * liuyijun
	 */
	private void sendVoice(String content, String mobile) {
		StringBuilder path = new StringBuilder("?");
		path.append("phone=" + mobile);
		path.append("&content=" + content);
		path.append("&type=systme_voice&from=vfunding-www");
		this.senderVoiceRestInvokerFactory.getRestInvoker().get(
				path.toString(), Integer.class);
	}

}
