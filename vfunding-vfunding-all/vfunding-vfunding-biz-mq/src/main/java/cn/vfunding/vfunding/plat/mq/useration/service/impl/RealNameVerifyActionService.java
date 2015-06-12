package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.common.framework.utils.beans.EmptyUtil;
import cn.vfunding.vfunding.biz.jmssender.service.impl.JmsSenderService;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;
import cn.vfunding.vfunding.common.sendconfig.ISendConfigUtil;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class RealNameVerifyActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private JmsSenderService jmsSenderUtil;

	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doRealNameVerify(AfterActionMessage msg) {
		try {
			Integer r = Integer.parseInt(msg.getMethodReturn().toString());
			User record = JSON.parseObject(msg.getMethodArgs()[0].toString(),
					User.class);
			if (r > 0) {
				this.doRealNameVerifyBiz(record, msg.getActionName());
			} else {
				logger.info("*****[system  " + record.getUserId() + " 实名认证 失败]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system  " + record.getUserId()
					+ " 实名认证 MQ消息处理成功]");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			logger.error(e.getMessage());
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}

	}

	private void doRealNameVerifyBiz(User record, String actionName) {
		// 实名认证相关活动处理
		synchroDataService.doSynchroData(record,
				"/activityUserAction/realName", actionName, "activity",
				record.getUserId());
		// 财经道过来的用户 实名同步接口
		synchroDataService.doSynchroData(record, "/cjdUserAction/realName",
				actionName, "cjd", record.getUserId());
		// 发送实名认证邮件
		if (EmptyUtil.isNotEmpty(record.getEmail())) {
			this.sendEmail(record.getEmail(),
					ISendConfigUtil.EMAIL_TEMPLET_REALNAME,
					record.getUsername(), record.getUsername());
		}
	}

	/**
	 * 发送邮件
	 * 
	 * @param email
	 * @param emailKey
	 * @param args
	 */
	private void sendEmail(String email, String emailKey, String... args) {
		this.jmsSenderUtil.sendEmail(email, emailKey, args);
	}

}
