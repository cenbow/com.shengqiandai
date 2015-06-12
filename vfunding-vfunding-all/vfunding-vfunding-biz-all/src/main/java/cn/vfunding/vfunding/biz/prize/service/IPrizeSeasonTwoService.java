package cn.vfunding.vfunding.biz.prize.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.vfunding.biz.prize.model.Prize;
import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;
import cn.vfunding.vfunding.biz.prize.vo.ActivityPrizeVO;
import cn.vfunding.vfunding.biz.session.utils.UserSession;

public interface IPrizeSeasonTwoService {
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
    
    /**
     * #邀请最多实名注册用户理财师(活动期间)
     * @return
     */
    List<ActivityPrizeVO> selectRegisterAndSetRealNameFromInvite();
    
    /**
     * #邀请最多用户投资(活动期间)
     * @return
     */
    List<ActivityPrizeVO> selectTenderFromInvite();
    
    /**
     * #邀请最多投资金额理财师(活动期间)
     * @return
     */
    List<ActivityPrizeVO> selectAccountFromInvite(); 
}
