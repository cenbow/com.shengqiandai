package cn.vfunding.union.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.union.plat.mq.service.IInvestInfoService;
import cn.vfunding.vfunding.common.module.activemq.message.InvestMessage;
/**
 * 用户投资时的网站联盟用户分利计算的消息接收对象
 * @author liuyijun
 *
 */
@Component
public class InvestInfoReceiver extends MessageReceiver{ 
	@Autowired
	private IInvestInfoService infoService;
	Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void receive(Object message) {
		InvestMessage ms=(InvestMessage) message;
		this.infoService.insertByInvestMessage(ms);
	}
	
	@PostConstruct
	public void setMessageInfo(){
		super.setMessageClass("cn.vfunding.common.module.activemq.message.InvestMessage");
	}

}
