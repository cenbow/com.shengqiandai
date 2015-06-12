package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;

public interface IBorrowCronService {

	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.BorrowCronServiceImpl",methodName="flowBorrow",
			cronName = "自动流标", cronRemark = "自动流标", systemName = "自动流标",
			systemType = "vfunding-vfunding",reStartPath="/cron/borrow/reStartFlowBorrow")
	String flowBorrow();
	
	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.BorrowCronServiceImpl",methodName="willRepayBorrow",
			cronName = "到期还款通知", cronRemark = "到期还款通知", systemName = "到期还款通知",
			systemType = "vfunding-vfunding",reStartPath="/cron/borrow/reStartWillRepayBorrow")
	String willRepayBorrow();
}