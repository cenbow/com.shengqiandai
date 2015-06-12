package cn.vfunding.vfunding.biz.mobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.mobile.dao.MobileResponseLogMapper;
import cn.vfunding.vfunding.biz.mobile.model.MobileResponseLog;
import cn.vfunding.vfunding.biz.mobile.service.IMobileResponseLogService;
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
