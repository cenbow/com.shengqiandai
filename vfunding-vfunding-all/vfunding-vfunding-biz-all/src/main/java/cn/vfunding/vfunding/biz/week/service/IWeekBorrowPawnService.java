package cn.vfunding.vfunding.biz.week.service;

import cn.vfunding.vfunding.biz.week.model.WeekBorrow;
import cn.vfunding.vfunding.biz.week.model.WeekBorrowPawn;

public interface IWeekBorrowPawnService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(WeekBorrowPawn record);

	WeekBorrowPawn selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekBorrowPawn record);

	WeekBorrowPawn selectByBorrowId(Integer borrowId);
	
	int saveWeekBorrowPawn(WeekBorrow weekBorrow,WeekBorrowPawn weekBorrowPawn);    

}