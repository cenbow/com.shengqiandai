package cn.vfunding.vfunding.biz.achievement.service;

import cn.vfunding.vfunding.biz.achievement.model.Achievement;

public interface IAchievementService {
    int deleteByPrimaryKey(Integer id);

    int insert(Achievement record);

    int insertSelective(Achievement record);

    Achievement selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Achievement record);

    int updateByPrimaryKey(Achievement record);
}