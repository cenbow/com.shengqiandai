package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;

@Component
public class SendCommonReceiver extends MessageReceiver {

	@Resource(name = "senderSmsRestInvokerFactory")
	private RestInvokerFactory senderSmsRestInvokerFactory;
	
	@Resource(name = "senderEmailRestInvokerFactory")
	private RestInvokerFactory senderEmailRestInvokerFactory;


	@Override
	public void receive(Object message) {
		SendCommon ms = (SendCommon) message;
		if(EmptyUtil.isNotEmpty(ms.getEmail())){
			this.sendEmail(ms);
		}else{
			this.sendSms(ms);
		}
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.common.framework.send.util.SendCommon");
	}

	
	private void sendSms(SendCommon ms) {
		this.senderSmsRestInvokerFactory.getRestInvoker().post("/byTemplet", ms);
	}
	
	private void sendEmail(SendCommon ms) {
		this.senderEmailRestInvokerFactory.getRestInvoker().post("/byTemplet", ms);
	}

}
