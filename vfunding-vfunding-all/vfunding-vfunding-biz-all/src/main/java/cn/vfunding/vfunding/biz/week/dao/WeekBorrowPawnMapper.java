package cn.vfunding.vfunding.biz.week.dao;

import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;

public interface WeekBorrowPawnMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekBorrowPawn record);

    int insertSelective(WeekBorrowPawn record);

    WeekBorrowPawn selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeekBorrowPawn record);

    int updateByPrimaryKey(WeekBorrowPawn record);
    
    WeekBorrowPawn selectByBorrowId(Integer id);
}