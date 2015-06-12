package cn.vfunding.vfunding.biz.week.service;

import cn.vfunding.vfunding.biz.week.model.WeekUser;
import cn.vfunding.vfunding.biz.week.vo.WeekUserVO;

public interface IWeekUserService {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekUser record);

    int insertSelective(WeekUser record);

    WeekUser selectByPrimaryKey(Integer id);
    
    WeekUser selectByEmpId(Integer id);
    
    WeekUserVO selectWeekUserByEmpId(Integer id);

    int updateByPrimaryKeySelective(WeekUser record);

    int updateByPrimaryKey(WeekUser record);
    
    
}