package cn.vfunding.vfunding.biz.sina.service;

import cn.vfunding.vfunding.biz.account.model.AccountCash;
import cn.vfunding.vfunding.biz.common.vo.AccountCashVO;
import cn.vfunding.vfunding.common.interceptors.annotations.AfterAction;

public interface IAccountCashSinaService {
	/**
	 * 新浪托管提现
	 * @param accountCash
	 * @return
	 * @throws Exception
	 * @author jianwei
	 */
	@AfterAction(actionName="sinaTakeCash")
	String doUserTakeCash(AccountCash accountCash, AccountCashVO cashVO) throws Exception;

}
