package cn.vfunding.vfunding.biz.cron.service;

import cn.vfunding.common.framework.utils.cron.annotation.CronInfoAnnotation;



public interface IFinancialPlannerCronService {

	@CronInfoAnnotation(className="cn.vfunding.vfunding.biz.cron.service.impl.FinancialPlannerCronServiceImpl",
			methodName="selectUserFinancialplanner",cronName="定时处理用户理财师升降级",
			cronRemark="定时处理用户理财师升降级",systemName="佣兵计划",
			systemType="vfunding-vfunding",reStartPath="/cron/financialPlanner/reStartFinancialPlanner")
	String selectUserFinancialplanner();
}
