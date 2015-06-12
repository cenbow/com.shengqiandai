package cn.vfunding.vfunding.biz.hikes.dao;

import cn.vfunding.vfunding.biz.hikes.model.HikesCardLog;

public interface HikesCardLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HikesCardLog record);

    int insertSelective(HikesCardLog record);

    HikesCardLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HikesCardLog record);

    int updateByPrimaryKey(HikesCardLog record);
}