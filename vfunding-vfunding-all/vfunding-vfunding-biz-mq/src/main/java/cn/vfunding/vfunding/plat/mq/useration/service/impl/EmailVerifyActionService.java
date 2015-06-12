package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.system.service.IJmsLogService;
import cn.vfunding.vfunding.biz.user.model.User;
import cn.vfunding.vfunding.common.module.activemq.message.afteraction.AfterActionMessage;

import com.alibaba.fastjson.JSON;

/**
 * 用户成功投资后相关业务处理服务类
 * 
 * @author liuyijun
 * 
 */
@Component
public class EmailVerifyActionService {

	/**
	 * 日志对象，slf4j下的对象
	 */
	Logger logger = LoggerFactory.getLogger("actionlog");

	@Autowired
	private UserActionSynchroDataService synchroDataService;

	@Autowired
	private IJmsLogService mapper;

	@Async
	public void doEmailVerify(AfterActionMessage msg) {
		try {
			Integer r = Integer.parseInt(msg.getMethodReturn().toString());
			User record = JSON.parseObject(msg.getMethodArgs()[0].toString(),
					User.class);
			if (r > 0) {
				this.doEmailVerifyBiz(record, msg.getActionName());
			} else {
				logger.info("*****[system  " + record.getUserId() + " 邮箱认证 失败]");
			}
			JmsLogUtil.doLog(mapper, msg.getMessageId(), true);
			logger.info("*****[system  " + record.getUserId()
					+ " 邮箱认证MQ消息处理成功]");
		} catch (Exception e) {
			logger.error(e.getMessage());
			logger.error(e.getStackTrace().toString());
			e.printStackTrace();
			JmsLogUtil.doLog(mapper, msg.getMessageId(), false);
		}

	}

	private void doEmailVerifyBiz(User record, String actionName) {
		// 邮箱认证相关活动处理
		synchroDataService.doSynchroData(record,
				"/activityUserAction/emailVerify", actionName, "activity",
				record.getUserId());
	}

}
