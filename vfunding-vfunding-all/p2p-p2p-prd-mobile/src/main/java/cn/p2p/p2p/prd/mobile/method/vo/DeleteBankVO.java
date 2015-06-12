package cn.p2p.p2p.prd.mobile.method.vo;

import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;

public class DeleteBankVO extends GeneralRequestVO{
	private Integer accountId;
	public Integer getAccountId() {
		return accountId;
	}
	public void setAccountId(Integer accountId) {
		this.accountId = accountId;
	}
}
