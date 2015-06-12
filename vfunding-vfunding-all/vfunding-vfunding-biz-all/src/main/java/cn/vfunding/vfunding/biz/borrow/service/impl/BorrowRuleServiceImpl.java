package cn.vfunding.vfunding.biz.borrow.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.borrow.dao.BorrowRuleMapper;
import cn.vfunding.vfunding.biz.borrow.model.BorrowRule;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowRuleService;
@Service("borrowRuleService")
public class BorrowRuleServiceImpl implements IBorrowRuleService {

	@Autowired
	private BorrowRuleMapper borrowRuleMapper;
	
	@Override
	public int deleteByPrimaryKey(Integer borrowId) {
		// TODO Auto-generated method stub
		return borrowRuleMapper.deleteByPrimaryKey(borrowId);
	}


	@Override
	public int insertSelective(BorrowRule record) {
		// TODO Auto-generated method stub
		return borrowRuleMapper.insertSelective(record);
	}

	@Override
	public BorrowRule selectByPrimaryKey(Integer borrowId) {
		// TODO Auto-generated method stub
		return borrowRuleMapper.selectByPrimaryKey(borrowId);
	}

	@Override
	public int updateByPrimaryKeySelective(BorrowRule record) {
		// TODO Auto-generated method stub
		return borrowRuleMapper.updateByPrimaryKeySelective(record);
	}


}
