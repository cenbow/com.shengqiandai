package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IUserRegisterComplimentCronService {
	/**
	 * 用户注册问候短信
	 * @return
	 */
	@CronInfoAnnotation(className = "cn.vfunding.vfunding.biz.cron.service.impl.UserRegisterComplimentCronServiceImpl", methodName = "userRegisterCompliment", cronName = "用户注册问候短信", cronRemark = "用户注册问候短信", systemName = "用户注册问候短信", systemType = "vfunding-vfunding", reStartPath = "/cron/userRegister/compliment")
	String userRegisterCompliment();
}
