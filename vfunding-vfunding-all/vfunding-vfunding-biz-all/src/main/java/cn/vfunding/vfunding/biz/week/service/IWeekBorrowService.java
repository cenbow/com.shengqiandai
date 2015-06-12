package cn.vfunding.vfunding.biz.week.service;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;

public interface IWeekBorrowService {
	int deleteByPrimaryKey(Integer id);

	int insertSelective(WeekBorrow record);

	WeekBorrow selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(WeekBorrow record);

	List<WeekBorrow> selectWeekBorrowByWeekId(Integer weekId);
    
    int weekBorrowSubTrial(WeekBorrow record);
    
    List<WeekBorrow> selectWeekBorrowByWeekIdListPage(PageSearch pageSearch);
    
    int saveWeekBorrow(WeekBorrow weekBorrow);
    
}