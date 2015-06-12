package cn.vfunding.vfunding.biz.week.service;

import cn.vfunding.vfunding.biz.week.model.WeekRate;


public interface IWeekRateService {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekRate record);

    int insertSelective(WeekRate record);

    WeekRate selectByPrimaryKey(Integer id);
        
    int updateByPrimaryKeySelective(WeekRate record);

    int updateByPrimaryKey(WeekRate record);
    
    
}