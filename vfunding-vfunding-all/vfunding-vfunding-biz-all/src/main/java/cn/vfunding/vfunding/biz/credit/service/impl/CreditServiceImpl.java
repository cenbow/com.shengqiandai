package cn.vfunding.vfunding.biz.credit.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.credit.dao.CreditMapper;
import cn.vfunding.vfunding.biz.credit.model.Credit;
import cn.vfunding.vfunding.biz.credit.service.ICreditService;
@Service("creditService")
public class CreditServiceImpl implements ICreditService {

	@Autowired
	private CreditMapper mapper;
	@Override
	public int insert(Credit record) {
		return this.mapper.insert(record);
	}

	@Override
	public int insertSelective(Credit record) {
		return this.mapper.insertSelective(record);
	}

	@Override
	public Integer selectValueByUserId(Integer userId) {
		return this.mapper.selectValueByUserId(userId);
	}

	@Override
	public int updataByUserId(Credit record) {
		return this.mapper.updataByUserId(record);
	}

}
