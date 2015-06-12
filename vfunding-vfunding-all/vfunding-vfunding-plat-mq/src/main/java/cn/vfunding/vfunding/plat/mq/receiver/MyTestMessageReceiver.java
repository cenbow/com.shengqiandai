package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.common.module.activemq.message.MyTestMessage;

/**
 * 红包发放
 * 
 * @author lilei
 * 
 */
@Component
public class MyTestMessageReceiver extends MessageReceiver {

	
	@Override
	public void receive(Object message) {
		MyTestMessage ms = (MyTestMessage) message;
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(ms.getName());
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.MyTestMessage");
	}

}
