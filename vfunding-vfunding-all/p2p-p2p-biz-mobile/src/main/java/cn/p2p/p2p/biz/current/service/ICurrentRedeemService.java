package cn.p2p.p2p.biz.current.service;

import cn.p2p.p2p.biz.current.model.CurrentRedeem;
import cn.p2p.p2p.biz.current.vo.UserCurrentAccountVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface ICurrentRedeemService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CurrentRedeem record);

    CurrentRedeem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentRedeem record);
    
    @NeedLock
    @AfterAction(actionName = "currentRedeem")
    MobileBaseResponse userRedeem(UserCurrentAccountVO vo);

}