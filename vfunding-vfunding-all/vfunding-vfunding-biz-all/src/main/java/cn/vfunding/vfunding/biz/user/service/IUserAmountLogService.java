package cn.vfunding.vfunding.biz.user.service;

import cn.vfunding.common.framework.easyui.page.utils.Json;
import cn.vfunding.common.framework.lock.NeedLock;
import cn.vfunding.vfunding.biz.common.vo.AmountVO;
import cn.vfunding.vfunding.biz.common.vo.ApplyAmountVO;
import cn.vfunding.vfunding.biz.user.model.UserAmountlog;

public interface IUserAmountLogService {
	int deleteByPrimaryKey(Integer id);

    int insert(UserAmountlog record);

    int insertSelective(UserAmountlog record);

    UserAmountlog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserAmountlog record);

    int updateByPrimaryKeyWithBLOBs(UserAmountlog record);

    int updateByPrimaryKey(UserAmountlog record);
    
    /**
     * @Description:后台审核额度
     * @return
     * @author liuhuan
     */
    @NeedLock
    int updateApplyAmount(AmountVO amountVO);

	Json applyOnline(ApplyAmountVO applyAmountvo);
}
