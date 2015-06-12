package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class RepaymentVO extends BaseVO{

	
	private Integer repaymentId;
	/**
	 * 是否是真实标的还款
	 */
	private boolean isRealBorrow;

	public Integer getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(Integer repaymentId) {
		this.repaymentId = repaymentId;
	}

	public boolean isRealBorrow() {
		return isRealBorrow;
	}

	public void setRealBorrow(boolean isRealBorrow) {
		this.isRealBorrow = isRealBorrow;
	}

	
	
	
	
}
