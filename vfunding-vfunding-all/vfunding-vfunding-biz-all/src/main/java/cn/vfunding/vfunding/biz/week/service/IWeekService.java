package cn.vfunding.vfunding.biz.week.service;

import java.util.List;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.utils.page.PageSearch;
import cn.vfunding.vfunding.biz.week.model.Week;
import cn.vfunding.vfunding.biz.week.model.WeekRate;
import cn.vfunding.vfunding.biz.week.vo.TrialBorrowVO;
import cn.vfunding.vfunding.biz.week.vo.WeekVO;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IWeekService {
    int deleteByPrimaryKey(Integer id);

    int insert(Week record);

    int insertSelective(Week record);

    Week selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Week record);

    int updateByPrimaryKey(Week record);
    
    Week selectWeekAndBorrowById(Integer id);
    
    int weekSubTrial(Week record);
    
    List<Week> selectWeekListPage(PageSearch pageSearch);
    
    List<WeekVO> selectWeekVOListPage(PageSearch pageSearch);
    
    Json saveWeek(Week week,WeekRate weekRate);
    
    List<Week> selectWeekForBuyListPage(PageSearch pageSearch);
    
    void updateRealityMoneyByWeekId(Integer id);
    
    Week selectIndexWeek();
    
    /**
     * 发布或保存7天理财产品
     * @param week
     * @param tbv
     * @param trialStatus
     * @return
     */
   //@NeedAfterInterceptor("weekExamine")
    @AfterAction(actionName="weekExamine")
    Json updateWeekStatus(Week week, TrialBorrowVO tbv, String trialStatus);
    
    Week selectByWeekPreview(Integer id);
    
}