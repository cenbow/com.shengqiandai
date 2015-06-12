package cn.p2p.p2p.biz.rechargeandtender.service;

import org.springframework.scheduling.annotation.Async;

import cn.p2p.p2p.biz.rechargeandtender.model.RechargeAndTender;
import cn.vfunding.common.framework.lock.NeedLock;

public interface IRechargeAndTenderService {
    int deleteByPrimaryKey(String tradeNo);

    int insertSelective(RechargeAndTender record);

    RechargeAndTender selectByPrimaryKey(String tradeNo);

    int updateByPrimaryKeySelective(RechargeAndTender record);
    
    @NeedLock("/recharge/tender")
    void rechargeTenderAction(String tradeNo,String str);

}