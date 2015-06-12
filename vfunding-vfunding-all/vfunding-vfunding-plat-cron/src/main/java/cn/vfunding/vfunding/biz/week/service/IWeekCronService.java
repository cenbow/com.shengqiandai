package cn.vfunding.vfunding.biz.week.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IWeekCronService {

	/**
	 * week扫描器
	 */
	@CronInfoAnnotation(className = "cn.vfunding.vfunding.biz.week.service.WeekCronServiceImpl", methodName = "updateWeekInfo", cronName = "周盈宝扫描器", cronRemark = "周盈宝扫描器", systemName = "活期宝认购记录扫描器", systemType = "vfunding-vfunding", reStartPath = "/cron/week/reStartUpdateWeekInfo")
	String updateWeekInfo();

}
