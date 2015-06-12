package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IAccountCronService {

	/**
	 * 用户闲置金额提醒
	 * @return
	 */
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.AccountCronServiceImpl",methodName="leaveUnusedMoney",
			cronName = "闲置金额提醒", cronRemark = "闲置金额提醒", systemName = "闲置金额提醒",
			systemType = "vfunding-vfunding",reStartPath="/cron/account/leaveUnusedMoney")
	String leaveUnusedMoney();
	
}