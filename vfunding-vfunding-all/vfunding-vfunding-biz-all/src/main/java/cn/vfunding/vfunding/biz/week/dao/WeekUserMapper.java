package cn.vfunding.vfunding.biz.week.dao;

import cn.vfunding.vfunding.biz.week.model.WeekUser;

public interface WeekUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekUser record);

    int insertSelective(WeekUser record);

    WeekUser selectByPrimaryKey(Integer id);
    
    WeekUser selectByEmpId(Integer id);

    int updateByPrimaryKeySelective(WeekUser record);

    int updateByPrimaryKey(WeekUser record);
}