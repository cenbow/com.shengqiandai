package cn.vfunding.vfunding.biz.current.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface ICurrentCronService {

	/**
	 * 活期宝扫描器
	 */
	@CronInfoAnnotation(className = "cn.vfunding.vfunding.biz.current.service.CurrentCronServiceImpl", methodName = "updateAllCurrentInfo", cronName = "活期宝扫描器", cronRemark = "活期宝扫描器", systemName = "活期宝扫描器", systemType = "vfunding-vfunding", reStartPath = "/cron/current/reStartUpdateAllCurrentInfo")
	String updateAllCurrentInfo();

}
