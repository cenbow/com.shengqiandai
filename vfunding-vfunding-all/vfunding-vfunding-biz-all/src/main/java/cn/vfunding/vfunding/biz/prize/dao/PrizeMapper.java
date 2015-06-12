package cn.vfunding.vfunding.biz.prize.dao;

import java.util.List;

import cn.vfunding.vfunding.biz.prize.model.Prize;
import cn.vfunding.vfunding.biz.prize.vo.ActivityPrizeVO;

public interface PrizeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Prize record);

    int insertSelective(Prize record);

    Prize selectByPrimaryKey(Integer id);
    
    List<Prize> selectListBySquad(String squad);
    
    int updateByPrimaryKeySelective(Prize record);

    int updateByPrimaryKey(Prize record);
    
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
    
    /**
     * #邀请最多用户投资(5月)
     * @return
     */
    List<ActivityPrizeVO> selectTenderFromInviteForMay();
    
    /**
     * #邀请最多投资金额理财师(5月)
     * @return
     */
    List<ActivityPrizeVO> selectAccountFromInviteForMay(); 
    
    
    
}