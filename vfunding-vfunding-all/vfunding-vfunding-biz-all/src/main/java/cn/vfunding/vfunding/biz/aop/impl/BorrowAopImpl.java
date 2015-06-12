package cn.vfunding.vfunding.biz.aop.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.vfunding.vfunding.biz.aop.IBorrowAop;
import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.borrow.service.IBorrowService;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;

@Component("borrowAop")
public class BorrowAopImpl implements IBorrowAop {

	@Autowired
	private IBorrowService borrowService;

	@Override
	public String updateRepayFeelBorrow(String repaymentId, Integer userId,
			String ip) {
		return this.borrowService.updateRepayFeelBorrow(repaymentId, userId, ip);
	}

	@Override
	public int updateVerifyBorrow(Borrow borrow, Integer userId,
			InvestorsFee investorsFee, MortgageCar car, String ip) {
		return this.borrowService.updateVerifyBorrow(borrow, userId, investorsFee, car, ip);
	}

	@Override
	public String updateRepayBorrow(String repaymentId, String ip) {
		return this.borrowService.updateRepayBorrow(repaymentId, ip);
	}


}
