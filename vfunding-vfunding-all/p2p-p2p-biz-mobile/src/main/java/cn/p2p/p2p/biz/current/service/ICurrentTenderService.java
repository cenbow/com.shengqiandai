package cn.p2p.p2p.biz.current.service;

import cn.p2p.p2p.biz.current.model.CurrentTender;
import cn.p2p.p2p.biz.current.vo.UserCurrentAccountVO;
import cn.p2p.p2p.biz.mobile.base.model.MobileBaseResponse;
import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface ICurrentTenderService {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(CurrentTender record);

    CurrentTender selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CurrentTender record);
    
    /**
     * 投资活期
     * @param vo
     * @return
     */
    @NeedLock
    @AfterAction(actionName = "currentTender")
    MobileBaseResponse UserCurrentTender(UserCurrentAccountVO vo);

}