package cn.p2p.p2p.biz.current.service;

import cn.p2p.p2p.biz.current.model.CurrentRedeemRule;

public interface ICurrentRedeemRuleService {
    int deleteByPrimaryKey(Integer userId);

    int insertSelective(CurrentRedeemRule record);

    CurrentRedeemRule selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(CurrentRedeemRule record);

}