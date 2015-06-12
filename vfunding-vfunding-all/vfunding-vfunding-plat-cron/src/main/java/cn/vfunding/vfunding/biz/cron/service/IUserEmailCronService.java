package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IUserEmailCronService {

	/**
	 * 长时间没有投资/闲置
	 * @return
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UserEmailCronServiceImpl",methodName="sendEmailForNoTender",
			cronName = "长时间没有投资/闲置", cronRemark = "长时间没有投资/闲置", systemName = "长时间没有投资/闲置",
			systemType = "vfunding-vfunding",reStartPath="/cron/user/sendEmailForNoTender")
	 String sendEmailForNoTender();
	
	/**
	 * 只注册验证邮箱的新用户
	 * @return
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.UserEmailCronServiceImpl",methodName="sendEmailForNoLogin",
			cronName = "只注册验证邮箱的新用户", cronRemark = "只注册验证邮箱的新用户", systemName = "只注册验证邮箱的新用户",
			systemType = "vfunding-vfunding",reStartPath="/cron/user/sendEmailForNoTender")
	 String sendEmailForNoLogin();
}
