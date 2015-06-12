package cn.vfunding.vfunding.biz.sina.service;

import java.math.BigDecimal;

public interface IAccountTransferSinaService {
	/**
	 * 转账接口
	 * @param payerId
	 * @param payeeId
	 * @param amount
	 * @return
	 * @author jianwei
	 */
	public String doAccpintTransfer(String payerId,String payeeId,BigDecimal amount)  throws Exception;
}
