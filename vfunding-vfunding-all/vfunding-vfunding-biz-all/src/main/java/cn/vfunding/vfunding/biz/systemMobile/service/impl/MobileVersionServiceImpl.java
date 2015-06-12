package cn.vfunding.vfunding.biz.systemMobile.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.systemMobile.dao.MobileVersionMapper;
import cn.vfunding.vfunding.biz.systemMobile.model.MobileVersion;
import cn.vfunding.vfunding.biz.systemMobile.service.IMobileVersionService;

@Service("mobileVersionService")
public class MobileVersionServiceImpl implements IMobileVersionService {

	@Autowired
	private MobileVersionMapper mvMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.mvMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(MobileVersion record) {
		// TODO Auto-generated method stub
		return this.mvMapper.insert(record);
	}

	@Override
	public int insertSelective(MobileVersion record) {
		// TODO Auto-generated method stub
		return this.mvMapper.insertSelective(record);
	}

	@Override
	public MobileVersion selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return this.mvMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(MobileVersion record) {
		// TODO Auto-generated method stub
		return this.mvMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(MobileVersion record) {
		// TODO Auto-generated method stub
		return this.mvMapper.updateByPrimaryKey(record);
	}

	@Override
	public MobileVersion selectByType(String type) {
		// TODO Auto-generated method stub
		return this.mvMapper.selectByType(type);
	}

}