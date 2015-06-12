package cn.vfunding.vfunding.biz.thirdplat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.thirdplat.dao.CashVolumeMapper;
import cn.vfunding.vfunding.biz.thirdplat.model.CashVolume;
import cn.vfunding.vfunding.biz.thirdplat.service.ICashVolumeService;

@Service("cashVolumeService")
public class CashVolumeImpl implements ICashVolumeService{

	@Autowired
	private CashVolumeMapper cashVolumeMapper;

	@Override
	public int insert(CashVolume record) {
		return cashVolumeMapper.insert(record);
	}

	@Override
	public int insertSelective(CashVolume record) {
		return cashVolumeMapper.insertSelective(record);
	}

	@Override
	public CashVolume selectByUserId(Integer userId) {
		// TODO Auto-generated method stub
		return this.cashVolumeMapper.selectByUserId(userId);
	}
	
	
	
	
}
