package cn.vfunding.vfunding.plat.mq.receiver;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.jms.activemq.MessageReceiver;
import cn.vfunding.vfunding.biz.mq.service.IUserTaskService;
import cn.vfunding.vfunding.common.module.activemq.message.UserTaskMessage;

/**
 * 新手任务
 * 
 * @author liuyijun
 * 
 */
@Component
public class UserTaskReceiver extends MessageReceiver {
	@Autowired
	private IUserTaskService userTaskService;
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public void receive(Object message) {
		UserTaskMessage ms = (UserTaskMessage) message;
		this.userTaskService.insertUserTaskInfo(ms.getUserId(), ms.getTaskId());
	}

	@PostConstruct
	public void setMessageInfo() {
		super.setMessageClass("cn.vfunding.vfunding.common.module.activemq.message.UserTaskMessage");
	}
 
}
