package cn.vfunding.vfunding.biz.jmssender.service.impl;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.send.util.SendCommon;
import cn.vfunding.common.framework.send.util.SendCommonUtil;
import cn.vfunding.common.framework.utils.beans.EncryptionUtil;
import cn.vfunding.common.framework.utils.http.RestInvokerFactory;
import cn.vfunding.vfunding.biz.system.dao.JmsLogMapper;
import cn.vfunding.vfunding.biz.system.model.JmsLog;
import cn.vfunding.vfunding.common.jmssender.JmsSenderObj;
import cn.vfunding.vfunding.common.jmssender.SenderObjBuilder;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

@Component
public class JmsSenderService {

	@Autowired
	private JmsLogMapper jmsLogMapper;

	@Autowired
	@Qualifier("jmsSenderCenterRestInvokerFactory")
	private RestInvokerFactory factory;

	public void sendSms(String mobile, String smsKey, String... args) {
		SendCommon sendCommon = SendCommonUtil.createSmsSendCommon(mobile,
				smsKey, args);
		// this.jmsSystemSender.asynSendSystemJms(sendCommon);
		this.asynSendSystemJms(sendCommon);
	}

	@Async
	public void sendEmail(String email, String emailKey, String... args) {
		SendCommon sendCommon = SendCommonUtil.createEmailSendCommon(email,
				emailKey, args);
		// this.jmsSystemSender.sendAsynchronousMessage(sendCommon);
		this.asynSendSystemJms(sendCommon);
	}

	@Async
	public void asynSendSystemJms(Serializable message) {
		JmsSenderObj obj = this.createObjByMessage(message,
				SenderObjBuilder.JMS_SYSTEM);
		this.factory.getRestInvoker().post("", obj);
	}

	@Async
	public void asynSendUserActionJms(AfterActionMessage actionMessage) {
		this.jmsLogMapper.insert(this.createLogByActionMessage(actionMessage));
		JmsSenderObj obj = this.createObjByMessage(actionMessage,
				SenderObjBuilder.JMS_USERACTION);
		this.factory.getRestInvoker().post("", obj);
		// this.jmsUserActionSender.sendAsynchronousMessage(actionMessage);
	}

	private JmsSenderObj createObjByMessage(Serializable message,
			String destinationName) {
		String content = JSON.toJSONString(message);
		JmsSenderObj obj = SenderObjBuilder.buildJmsSenderObj(content,
				message.getClass(), destinationName);
		return obj;
	}

	private JmsLog createLogByActionMessage(AfterActionMessage actionMessage) {
		JmsLog log = new JmsLog();
		log.setJmsCategory(actionMessage.getActionName());
		log.setJmsId(actionMessage.getMessageId());
		String jsonMessage = JSON.toJSONString(actionMessage);
		String info = EncryptionUtil.encrypt(jsonMessage);
		log.setJmsInfo(info);
		log.setJmsStatus(0);
		log.setSendTime(new Date());
		return log;
	}
	
	
	
	


}
