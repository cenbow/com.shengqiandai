package cn.vfunding.vfunding.biz.achievement.dao;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Param;

import cn.vfunding.vfunding.biz.achievement.model.AchievementLog;

public interface AchievementLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AchievementLog record);

    int insertSelective(AchievementLog record);

    AchievementLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AchievementLog record);

    int updateByPrimaryKey(AchievementLog record);
    
    AchievementLog selectByAchievementIdAndUserId(@Param("achievementId") Integer achievementId,@Param("userId") Integer userId);
    
    BigDecimal sumHikesForApril(@Param("userId") Integer userId);
}