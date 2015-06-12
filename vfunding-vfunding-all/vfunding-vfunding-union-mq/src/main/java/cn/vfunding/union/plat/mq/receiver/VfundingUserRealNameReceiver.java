package cn.vfunding.union.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.union.plat.mq.service.IRealNameRecordService;
import cn.vfunding.vfunding.common.module.activemq.message.UserRealNameMessage;

/**
 * 微积金用户实名认证同步网站联盟用户实名认证消息接受者
 * 
 * @author liuyijun
 * 
 */
@Component
public class VfundingUserRealNameReceiver extends MessageReceiver {

	@Autowired
	private IRealNameRecordService realNameRecordService;

	@Override
	public void receive(Object message) {
		UserRealNameMessage ms = (UserRealNameMessage) message;
		this.realNameRecordService.vfundingUserRealName(ms);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.common.module.activemq.message.UserRealNameMessage");
	}

}
