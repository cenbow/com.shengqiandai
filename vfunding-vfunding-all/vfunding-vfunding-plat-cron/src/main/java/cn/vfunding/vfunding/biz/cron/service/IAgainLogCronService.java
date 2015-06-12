package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IAgainLogCronService {

	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.AgainLogCronServiceImpl",methodName="retryRequest",
			cronName = "补发机制", cronRemark = "补发机制", systemName = "补发机制",
			systemType = "vfunding-vfunding",reStartPath="/cron/againLog/retryRequest")
	String retryRequest();
	
}