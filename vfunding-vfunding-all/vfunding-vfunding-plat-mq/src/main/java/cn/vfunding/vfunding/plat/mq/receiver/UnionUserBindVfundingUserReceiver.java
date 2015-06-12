package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.common.vo.UnionUserBandVO;
import cn.vfunding.vfunding.biz.user.service.IUserService;

/**
 * 网站联盟用户通过身份证信息绑定微积金用户消息接收者
 * 
 * @author liuyijun
 * 
 */
@Component
public class UnionUserBindVfundingUserReceiver extends MessageReceiver {

	@Autowired
	private IUserService userService;

	@Override
	public void receive(Object message) {
		UnionUserBandVO unionUserBand = (UnionUserBandVO) message;
		this.userService.unionUserBandVfundingUser(unionUserBand);
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.biz.common.vo.UnionUserBandVO");
	}

}
