package cn.vfunding.vfunding.biz.achievement.service;

import java.math.BigDecimal;

import cn.vfunding.vfunding.biz.achievement.model.AchievementLog;

public interface IAchievementLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementLog record);

    int insertSelective(AchievementLog record);

    AchievementLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementLog record);

    int updateByPrimaryKey(AchievementLog record);
    
    AchievementLog selectByAchievementIdAndUserId(Integer achievementId,Integer userId);
    
    /**
     * 2015年四月活动累计加息
     * @param userId
     * @return
     * @author louchen 2015-03-31
     */
    BigDecimal sumHikesForApril(Integer userId);
    
    /**
     * 创建成就记录
     * @param achievementId
     * @param userId
     * @return
     * @author louchen 2015-03-31
     */
    int createAchievementLog(Integer achievementId,Integer userId);
    
    /**
     * 更新用户领取成就奖励
     * @param achievementId
     * @param userId
     * @return
     * @author louchen 2015-03-31
     */
    int pickAchievementLog(Integer achievementId,Integer userId);
}