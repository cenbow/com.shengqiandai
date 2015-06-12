package cn.vfunding.vfunding.biz.mobile.service;

import cn.vfunding.vfunding.biz.mobile.model.MobileResponseLog;

public interface IMobileResponseLogService {
	int deleteByPrimaryKey(Integer id);

    int insertSelective(MobileResponseLog record);

    MobileResponseLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MobileResponseLog record);

}
