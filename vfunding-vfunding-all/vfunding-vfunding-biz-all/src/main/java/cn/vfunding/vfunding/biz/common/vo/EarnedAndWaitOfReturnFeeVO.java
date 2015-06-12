package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class EarnedAndWaitOfReturnFeeVO extends BaseVO{

	private BigDecimal firstEarned;

	private BigDecimal firstWait;

	private BigDecimal secondEarned;

	private BigDecimal secondWait;

	private BigDecimal thirdEarned;

	private BigDecimal thirdWait;

	public BigDecimal getFirstEarned() {
		return firstEarned;
	}

	public void setFirstEarned(BigDecimal firstEarned) {
		this.firstEarned = firstEarned;
	}

	public BigDecimal getFirstWait() {
		return firstWait;
	}

	public void setFirstWait(BigDecimal firstWait) {
		this.firstWait = firstWait;
	}

	public BigDecimal getSecondEarned() {
		return secondEarned;
	}

	public void setSecondEarned(BigDecimal secondEarned) {
		this.secondEarned = secondEarned;
	}

	public BigDecimal getSecondWait() {
		return secondWait;
	}

	public void setSecondWait(BigDecimal secondWait) {
		this.secondWait = secondWait;
	}

	public BigDecimal getThirdEarned() {
		return thirdEarned;
	}

	public void setThirdEarned(BigDecimal thirdEarned) {
		this.thirdEarned = thirdEarned;
	}

	public BigDecimal getThirdWait() {
		return thirdWait;
	}

	public void setThirdWait(BigDecimal thirdWait) {
		this.thirdWait = thirdWait;
	}
}
