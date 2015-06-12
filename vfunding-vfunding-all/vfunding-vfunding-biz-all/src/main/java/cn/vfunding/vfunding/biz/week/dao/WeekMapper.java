package cn.vfunding.vfunding.biz.week.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.vo.WeekVO;

public interface WeekMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Week record);

    int insertSelective(Week record);

    Week selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Week record);

    int updateByPrimaryKey(Week record);
    
    Week selectWeekAndBorrowById(Integer id);
    
    List<Week> selectWeekListPage(PageSearch pageSearch);
    
    List<WeekVO> selectWeekVOListPage(PageSearch pageSearch);
    
    List<Week> selectWeekForBuyListPage(PageSearch pageSearch);
    
    void updateRealityMoneyByWeekId(Integer id);
    
    Week selectIndexWeek();
}