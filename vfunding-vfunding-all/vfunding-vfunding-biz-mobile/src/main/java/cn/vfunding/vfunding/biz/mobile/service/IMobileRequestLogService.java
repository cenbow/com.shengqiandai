package cn.vfunding.vfunding.biz.mobile.service;

import cn.vfunding.vfunding.biz.mobile.model.MobileRequestLog;

public interface IMobileRequestLogService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(MobileRequestLog record);

    MobileRequestLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileRequestLog record);

    int selectCountByOrderNo(String orderNo);
}
