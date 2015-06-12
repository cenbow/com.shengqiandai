package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.common.vo.UnionUserRegisterVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 网站联盟用户注册接收对象
 * 
 * @author liuyijun
 * 
 */
@Component
public class UnionUserRegisterReceiver extends MessageReceiver {

	@Autowired
	private IUserService userService;

	@Override
	public void receive(Object message) {
		UnionUserRegisterVO unionUserRegister = (UnionUserRegisterVO) message;
		this.userService.unionUserRegister(unionUserRegister);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.biz.common.vo.UnionUserRegisterVO");
	}

}
