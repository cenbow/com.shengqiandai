package cn.vfunding.vfunding.biz.prize.service;

import cn.vfunding.vfunding.biz.prize.model.PrizeChooseRule;

public interface IPrizeChooseRuleService {
    int deleteByPrimaryKey(Integer userId);

    int insert(PrizeChooseRule record);

    int insertSelective(PrizeChooseRule record);

    PrizeChooseRule selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(PrizeChooseRule record);

    int updateByPrimaryKey(PrizeChooseRule record);
}
