package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;


public interface IUserUnLockService {
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UserUnLockServiceImpl",methodName="deleteUserLock",cronName="定时处理用户解除冻结状态",cronRemark="定时处理用户解除冻结状态",
			systemName="用户解冻",systemType="vfunding-vfunding",reStartPath="/cron/userUnlock/reStartUnlock")
	String deleteUserLock();
}
