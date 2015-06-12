package cn.vfunding.vfunding.biz.activity.service;

import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.biz.activity.model.ActivityLottery;

public interface IActivityLotteryService {
    int deleteByPrimaryKey(Integer id);

    int insert(ActivityLottery record);

    int insertSelective(ActivityLottery record);

    ActivityLottery selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ActivityLottery record);

    int updateByPrimaryKey(ActivityLottery record);
    
    ActivityLottery selectByActivityAndUserId(String activity,Integer userId);
    
    /**
     * 用户是否领取过4月活动彩票
     * @param userId
     * @return
     */
    int hasAprilLottery(Integer userId);
    
    /**
     * 用户是否可以领取4月活动彩票
     * @param userId
     * @return
     */
    Boolean canPickAprilLottery(Integer userId);
    
    /**
     * 领取4月活动彩票
     * @param userId
     * @return
     */
    @NeedLock("/locks/loadAprilLottery")
    String loadAprilLottery(Integer userId);
    
    
    /**
     * 发送4月活动彩票到礼品盒其他(vf_giftother_message标)
     * @param userId
     * @return
     */
    @NeedLock("/locks/sendGiftOhterMessage")
    String sendGiftOhterMessage(Integer userId);
}