package cn.vfunding.vfunding.biz.prize.service;

import javax.servlet.http.HttpServletRequest;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.prize.model.Prize;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

public interface IPrizeService {
    int deleteByPrimaryKey(Integer id);

    int insert(Prize record);

    int insertSelective(Prize record);

    Prize selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
    
    //@NeedLock("/locks/prize")
    Json choosePrize(UserSession user,HttpServletRequest request,String squad);
    
    PrizeChooseRule getUserPrizeChooseRule(Integer userId);
    
    PrizeChooseRule addUserPrizeChooseCount(Integer userId,Integer type);
}
