package cn.vfunding.vfunding.biz.week.dao;

import cn.vfunding.vfunding.biz.week.model.WeekRate;


public interface WeekRateMapper {
    int deleteByPrimaryKey(Integer weekId);

    int insert(WeekRate record);

    int insertSelective(WeekRate record);

    WeekRate selectByPrimaryKey(Integer weekId);

    int updateByPrimaryKeySelective(WeekRate record);

    int updateByPrimaryKey(WeekRate record);
}