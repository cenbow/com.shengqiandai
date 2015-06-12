package cn.p2p.p2p.biz.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.p2p.p2p.biz.mobile.dao.MobileResponseLogMapper;
import cn.p2p.p2p.biz.mobile.model.MobileResponseLog;
import cn.p2p.p2p.biz.mobile.service.IMobileResponseLogService;
@Service
public class MobileResponseLogServiceImpl implements IMobileResponseLogService{

	@Autowired
	private MobileResponseLogMapper mobileResponseLogMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.mobileResponseLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insertSelective(MobileResponseLog record) {
		return this.mobileResponseLogMapper.insertSelective(record);
	}

	@Override
	public MobileResponseLog selectByPrimaryKey(Integer id) {
		return this.mobileResponseLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MobileResponseLog record) {
		return this.mobileResponseLogMapper.updateByPrimaryKeySelective(record);
	}

}
