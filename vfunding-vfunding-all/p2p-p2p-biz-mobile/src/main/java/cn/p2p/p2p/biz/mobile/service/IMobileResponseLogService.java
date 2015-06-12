package cn.p2p.p2p.biz.mobile.service;

import cn.p2p.p2p.biz.mobile.model.MobileResponseLog;

public interface IMobileResponseLogService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(MobileResponseLog record);

    MobileResponseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileResponseLog record);

}
