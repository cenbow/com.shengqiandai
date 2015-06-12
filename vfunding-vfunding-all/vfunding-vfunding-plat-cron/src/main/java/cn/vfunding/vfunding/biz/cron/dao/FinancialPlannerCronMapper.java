package cn.vfunding.vfunding.biz.cron.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.cron.model.FinancialPlannerCron;

public interface FinancialPlannerCronMapper {

	void callProcedure();
	
	List<FinancialPlannerCron> selectUserFinancialplanner();
}