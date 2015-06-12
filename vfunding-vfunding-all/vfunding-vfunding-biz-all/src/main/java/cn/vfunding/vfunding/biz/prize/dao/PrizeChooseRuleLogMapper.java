package cn.vfunding.vfunding.biz.prize.dao;

import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRuleLog;

public interface PrizeChooseRuleLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PrizeChooseRuleLog record);

    int insertSelective(PrizeChooseRuleLog record);

    PrizeChooseRuleLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrizeChooseRuleLog record);

    int updateByPrimaryKey(PrizeChooseRuleLog record);
    
    PrizeChooseRuleLog selectByEntity(PrizeChooseRuleLog entity);
}