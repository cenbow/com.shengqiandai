package cn.vfunding.vfunding.biz.hikes.dao;

import cn.vfunding.vfunding.biz.hikes.model.HikesCard;

public interface HikesCardMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(HikesCard record);

    int insertSelective(HikesCard record);

    HikesCard selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(HikesCard record);

    int updateByPrimaryKey(HikesCard record);
    
    int selectUnLookCount(Integer userId);
}