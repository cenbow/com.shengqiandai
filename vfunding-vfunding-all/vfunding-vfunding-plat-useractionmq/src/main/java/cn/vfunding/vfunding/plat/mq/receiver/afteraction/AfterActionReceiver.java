package cn.vfunding.vfunding.plat.mq.receiver.afteraction;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;
import cn.vfunding.vfunding.plat.mq.useraction.service.IUserActionService;

/**
 * 动作后通知消息接收对象
 * 
 * @author liuyijun
 * 
 */
@Component
public class AfterActionReceiver extends MessageReceiver {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private IUserActionService userActionService;

	@Override
	public void receive(Object message) {
		AfterActionMessage ms = (AfterActionMessage) message;
		logger.info("*****[system mq " + ms.getActionName() + " 开始]");
		try {
			userActionService.doUserAction(ms);
			logger.info("*****[system mq " + ms.getActionName() + " 结束]");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("#####[system mq " + ms.getActionName() + " 失败]:["
					+ e.getMessage() + "]");
		}
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage");
	}

}
