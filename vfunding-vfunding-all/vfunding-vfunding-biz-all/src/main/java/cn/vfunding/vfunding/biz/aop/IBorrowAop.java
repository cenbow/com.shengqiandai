package cn.vfunding.vfunding.biz.aop;

import cn.vfunding.vfunding.biz.borrow.model.Borrow;
import cn.vfunding.vfunding.biz.borrow.model.MortgageCar;
import cn.vfunding.vfunding.biz.system.model.InvestorsFee;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IBorrowAop {

	
	//@NeedAfterInterceptor("pepayFeelBorrow")
	@AfterAction(actionName="pepayFeelBorrow")
	String updateRepayFeelBorrow(String repaymentId, Integer userId, String ip);
	
	/**
	 * @Description:初审 admin 后台审核人员
	 * @author liuhuan
	 */
	//@NeedAfterInterceptor("borrowExamine")
	@AfterAction(actionName="borrowExamine")
	int updateVerifyBorrow(Borrow borrow, Integer userId, InvestorsFee investorsFee,MortgageCar car, String ip);
	
	
	//@NeedAfterInterceptor("pepayBorrow")
	@AfterAction(actionName="pepayBorrow")
	String updateRepayBorrow(String repaymentId, String ip);
}
