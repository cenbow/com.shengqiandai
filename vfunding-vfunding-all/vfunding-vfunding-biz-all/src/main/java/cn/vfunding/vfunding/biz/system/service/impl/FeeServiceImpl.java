package cn.vfunding.vfunding.biz.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.FeeMapper;
import cn.vfunding.vfunding.biz.system.model.Fee;
import cn.vfunding.vfunding.biz.system.service.IFeeService;
@Service("feeService")
public class FeeServiceImpl implements IFeeService {

	@Autowired
	private FeeMapper feeMapper;
	
	@Override
	public Fee selectByPrimaryKey(Integer id) {
		return feeMapper.selectByPrimaryKey(id);
	}
	
	@Cacheable(value = "selectFeeCache", key = "#timeLimit")
	@Override
	public Fee selectByTimeLimit(Integer timeLimit) {
		return feeMapper.selectByTimeLimit(timeLimit);
	}

	
}
