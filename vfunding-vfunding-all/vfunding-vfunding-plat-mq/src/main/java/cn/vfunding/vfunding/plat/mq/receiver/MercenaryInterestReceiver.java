package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.mq.service.IMercenaryService;
import cn.vfunding.vfunding.common.module.activemq.message.MercenaryMessage;
/**
 * 好友返利消息接收对象
 * @author liuyijun
 *
 */
@Component
public class MercenaryInterestReceiver extends MessageReceiver{ 
	@Autowired
	private IMercenaryService mercenaryService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void receive(Object message) {
		MercenaryMessage ms=(MercenaryMessage) message;
		this.mercenaryService.insertMercenaryInterest(ms);
	}
	
	@PostConstruct
	public void setMessageInfo(){
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.MercenaryMessage");
	}

}
