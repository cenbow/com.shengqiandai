package cn.p2p.p2p.biz.current.dao;

import cn.p2p.p2p.biz.current.model.CurrentRedeemRule;

public interface CurrentRedeemRuleMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(CurrentRedeemRule record);

    int insertSelective(CurrentRedeemRule record);

    CurrentRedeemRule selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(CurrentRedeemRule record);

    int updateByPrimaryKey(CurrentRedeemRule record);
}