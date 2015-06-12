package cn.vfunding.vfunding.biz.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.vfunding.vfunding.biz.system.dao.InvestorsFeeMapper;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.biz.system.service.IInvestorsFeeService;
@Service("investorsFeeService")
public class InvestorsFeeServiceImpl implements IInvestorsFeeService {

	@Autowired
	private InvestorsFeeMapper investorFeeMapper;
	@Override
	public int insert(InvestorsFee record) {
		return investorFeeMapper.insert(record);
	}
	@Override
	public InvestorsFee selectByBorrowId(Integer borrowId) {
		return investorFeeMapper.selectByBorrowId(borrowId);
	}

}
