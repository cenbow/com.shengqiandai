package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.common.framework.lock.NeedLock;


public interface IBorrowRepaymentSinaService {
	
	/**
	 * 推送借款人还款
	 * @param repaymentId
	 * @return
	 * @throws Exception
	 */
	@NeedLock("/locks/repayBorrower")
	String doUserRepayBorrower(Integer repaymentId) throws Exception;
	
	/**
	 * 推送投资人收益
	 * @param repaymentId
	 * @return
	 * @throws Exception
	 */
	@NeedLock("/locks/repayTender")
	String doUserRepayTender(Integer repaymentId) throws Exception;
	
	/**
	 * 推送借款人还款和推送投资人收益
	 * @param repaymentId
	 * @return
	 * @throws Exception
	 */
	@NeedLock("/locks/repayBorrowerAndTender")
	String doUserRepayBorrowerAndTender(Integer repaymentId) throws Exception;
	
	/**
	 * 检查是否重复推送还款消息_借款人
	 * @param repaymentId
	 * @return
	 * @throws Exception
	 */
	Boolean checkRepeatRepayBorrower(Integer repaymentId) throws Exception;
	
	/**
	 * 检查是否重复推送还款消息_投资人
	 * @param repaymentId
	 * @return
	 * @throws Exception
	 */
	Boolean checkRepeatRepayTender(Integer repaymentId) throws Exception;
}
