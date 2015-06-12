package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface ISinaBonusCronService {
	
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.SinaBonusCronService",methodName="loadCreate",
			cronName = "存钱罐生息(中间表)", cronRemark = "存钱罐生息(中间表)", systemName = "存钱罐生息(中间表)",
			systemType = "vfunding-vfunding",reStartPath="/cron/sina/retrySinaBonusCreate")
	String loadCreate();
	
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.SinaBonusCronService",methodName="loadSync",
			cronName = "存钱罐生息", cronRemark = "存钱罐生息", systemName = "存钱罐生息",
			systemType = "vfunding-vfunding",reStartPath="/cron/sina/retrySinaBonusSync")
	String loadSync();
}
