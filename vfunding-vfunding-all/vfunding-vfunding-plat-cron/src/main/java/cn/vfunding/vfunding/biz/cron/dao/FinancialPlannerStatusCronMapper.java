package cn.vfunding.vfunding.biz.cron.dao;

import cn.vfunding.vfunding.biz.cron.model.FinancialPlannerStatusCron;



public interface FinancialPlannerStatusCronMapper {
    int insert(FinancialPlannerStatusCron record);

    int insertSelective(FinancialPlannerStatusCron record);
}