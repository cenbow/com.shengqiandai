package cn.vfunding.vfunding.biz.week.dao;

import java.util.List;

import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.week.model.WeekRepayment;
import cn.vfunding.vfunding.biz.week.vo.SearchWeekRepaymentVO;
import cn.vfunding.vfunding.biz.week.vo.WeekRepaymentVO;

public interface WeekRepaymentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WeekRepayment record);

    int insertSelective(WeekRepayment record);

    WeekRepayment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WeekRepayment record);

    int updateByPrimaryKey(WeekRepayment record);
    
    List<WeekRepayment> selectWeekRepaymentListPage(PageSearch pageSearch);
    
    List<WeekRepaymentVO> selectWeekRepaymentGroupByWeekIdListPage(PageSearch pageSearch);
    
    WeekRepaymentVO selectSumWeekRepayment(SearchWeekRepaymentVO searchWeekRepaymentVO);
}