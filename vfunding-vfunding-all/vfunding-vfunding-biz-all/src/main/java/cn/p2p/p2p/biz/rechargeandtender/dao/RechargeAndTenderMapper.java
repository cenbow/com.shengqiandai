package cn.p2p.p2p.biz.rechargeandtender.dao;

import cn.p2p.p2p.biz.rechargeandtender.model.RechargeAndTender;

public interface RechargeAndTenderMapper {
    int deleteByPrimaryKey(String tradeNo);

    int insert(RechargeAndTender record);

    int insertSelective(RechargeAndTender record);

    RechargeAndTender selectByPrimaryKey(String tradeNo);

    int updateByPrimaryKeySelective(RechargeAndTender record);

    int updateByPrimaryKey(RechargeAndTender record);
}