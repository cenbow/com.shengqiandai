package cn.vfunding.vfunding.biz.thirdplat.service;

import cn.vfunding.vfunding.biz.thirdplat.model.CashVolume;

public interface ICashVolumeService {

	int insert(CashVolume record);

	int insertSelective(CashVolume record);

	CashVolume selectByUserId(Integer userId);

}
