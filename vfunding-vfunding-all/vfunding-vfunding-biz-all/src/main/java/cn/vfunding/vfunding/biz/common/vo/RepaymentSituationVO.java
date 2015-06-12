package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class RepaymentSituationVO extends BaseVO{

	private Integer status;

	private Integer overdueRaymentLittle;

	private Integer overdueRaymentmore;

	private Integer overdueRaymentNo;

	private Integer advanceRayment;

	private Integer successRayment;

	private Integer loseRayment;

	private Integer waitRayment;

	private Integer successBorrow;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOverdueRaymentLittle() {
		return overdueRaymentLittle;
	}

	public void setOverdueRaymentLittle(Integer overdueRaymentLittle) {
		this.overdueRaymentLittle = overdueRaymentLittle;
	}

	public Integer getOverdueRaymentmore() {
		return overdueRaymentmore;
	}

	public void setOverdueRaymentmore(Integer overdueRaymentmore) {
		this.overdueRaymentmore = overdueRaymentmore;
	}

	public Integer getOverdueRaymentNo() {
		return overdueRaymentNo;
	}

	public void setOverdueRaymentNo(Integer overdueRaymentNo) {
		this.overdueRaymentNo = overdueRaymentNo;
	}

	public Integer getAdvanceRayment() {
		return advanceRayment;
	}

	public void setAdvanceRayment(Integer advanceRayment) {
		this.advanceRayment = advanceRayment;
	}

	public Integer getSuccessRayment() {
		if (successRayment == null) {
			successRayment = 0;
		}
		return successRayment;
	}

	public void setSuccessRayment(Integer successRayment) {
		this.successRayment = successRayment;
	}

	public Integer getLoseRayment() {
		if (loseRayment == null) {
			loseRayment = 0;
		}
		return loseRayment;
	}

	public void setLoseRayment(Integer loseRayment) {
		this.loseRayment = loseRayment;
	}

	public Integer getWaitRayment() {
		if (waitRayment == null) {
			waitRayment = 0;
		}
		return waitRayment;
	}

	public void setWaitRayment(Integer waitRayment) {
		this.waitRayment = waitRayment;
	}

	public Integer getSuccessBorrow() {
		return successBorrow;
	}

	public void setSuccessBorrow(Integer successBorrow) {
		this.successBorrow = successBorrow;
	}

}
