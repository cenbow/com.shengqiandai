package cn.vfunding.vfunding.biz.week.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.week.model.WeekBorrow;

public interface WeekBorrowMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekBorrow record);

    int insertSelective(WeekBorrow record);

    WeekBorrow selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeekBorrow record);

    int updateByPrimaryKey(WeekBorrow record);
    
    List<WeekBorrow> selectWeekBorrowByWeekId(Integer id);
    
    int updateByWeekIdSelective(WeekBorrow record);
    
    List<WeekBorrow> selectWeekBorrowByWeekIdListPage(PageSearch pageSearch);
}