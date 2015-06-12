package cn.vfunding.vfunding.biz.thirdplat.dao;

import cn.vfunding.vfunding.biz.thirdplat.model.JjCard;

public interface JjCardMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(JjCard record);

    int insertSelective(JjCard record);

    JjCard selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JjCard record);

    int updateByPrimaryKey(JjCard record);
    
    JjCard selectByNoSend(Integer gamePeriod);
}