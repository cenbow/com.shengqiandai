package cn.vfunding.vfunding.biz.system.service;

import cn.vfunding.vfunding.biz.system.model.InvestorsFee;

public interface IInvestorsFeeService {
	
	int insert(InvestorsFee record);
	
	InvestorsFee selectByBorrowId(Integer borrowId);
}
