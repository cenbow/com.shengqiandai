package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.UnionUserRealNameMessage;

/**
 * 网站联盟实名认证消息接收者
 * 
 * @author liuyijun
 * 
 */
@Component
public class UnionUserRealNameReceiver extends MessageReceiver {

	@Autowired
	private IUserService userService;

	@Override
	public void receive(Object message) {
		UnionUserRealNameMessage ms = (UnionUserRealNameMessage) message;
		this.userService.unionUserRealName(ms);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.UnionUserRealNameMessage");
	}

}
