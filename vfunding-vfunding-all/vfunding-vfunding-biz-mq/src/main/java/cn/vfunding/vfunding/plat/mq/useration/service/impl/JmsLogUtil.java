package cn.vfunding.vfunding.plat.mq.useration.service.impl;

import cn.vfunding.vfunding.biz.system.model.JmsLog;
import cn.vfunding.vfunding.biz.system.service.IJmsLogService;

public class JmsLogUtil {
	public static int doLog(IJmsLogService jmsLogService, String messageId,
			boolean success) {
		JmsLog newLog = new JmsLog();
		newLog.setJmsId(messageId);
		if (success) {
			newLog.setJmsResult("success");
		} else {
			newLog.setJmsResult("faild");
		}
		return jmsLogService.updateByPrimaryKeySelective(newLog);
	}

}
