package cn.vfunding.vfunding.biz.prize.service;

import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRuleLog;

public interface IPrizeChooseRuleLogService {
    int deleteByPrimaryKey(Integer id);

    int insert(PrizeChooseRuleLog record);

    int insertSelective(PrizeChooseRuleLog record);

    PrizeChooseRuleLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PrizeChooseRuleLog record);

    int updateByPrimaryKey(PrizeChooseRuleLog record);
}
