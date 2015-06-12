package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.user.service.IUserService;
import cn.vfunding.vfunding.common.module.activemq.message.RegisterMessage;

/**
 * 用户注册消息接收对象
 * @author liuyijun
 *
 */
@Component
public class RegisterReceiver extends MessageReceiver{

	@Autowired
	private IUserService userService;
	@Override
	public void receive(Object message) {
		RegisterMessage ms=(RegisterMessage) message;
		this.userService.doRegisterInfo(ms.getUserId(), ms.getRegisterIp());
	}
	
	@PostConstruct
	public void setMessageInfo(){
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.RegisterMessage");
	}

}
