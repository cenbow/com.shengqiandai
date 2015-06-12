package cn.vfunding.vfunding.biz.thirdplat.dao;

import cn.vfunding.vfunding.biz.thirdplat.model.CashVolume;

public interface CashVolumeMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(CashVolume record);

	int insertSelective(CashVolume record);

	CashVolume selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(CashVolume record);

	int updateByPrimaryKey(CashVolume record);

	CashVolume selectByUserId(Integer userId);
}