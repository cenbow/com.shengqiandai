package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IBirthdayUserCronService {

	/**
	 * 用户生日祝福邮件
	 * @return
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.BirthdayUserCronServiceImpl",methodName="sendEmailForBirthdayUser",
			cronName = "用户生日祝福邮件", cronRemark = "用户生日祝福邮件", systemName = "用户生日祝福邮件",
			systemType = "vfunding-vfunding",reStartPath="/cron/user/sendEmailForBirthdayUser")
	String sendEmailForBirthdayUser();
	
}
