package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.activity.service.IActivityHongbaoService;
import cn.vfunding.vfunding.common.module.activemq.message.ActivityHongbaoRegMessage;

/**
 * 红包发放
 * 
 * @author lilei
 * 
 */
@Component
public class ActivityHongbaoRegReceiver extends MessageReceiver {

	@Autowired
	private IActivityHongbaoService ahbService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void receive(Object message) {
		ActivityHongbaoRegMessage ms = (ActivityHongbaoRegMessage) message;
		logger.info("执行红包入账功能");
		this.ahbService.updateUserAccount(ms.getUserId(), ms.getPhone());
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.ActivityHongbaoRegMessage");
	}

}
