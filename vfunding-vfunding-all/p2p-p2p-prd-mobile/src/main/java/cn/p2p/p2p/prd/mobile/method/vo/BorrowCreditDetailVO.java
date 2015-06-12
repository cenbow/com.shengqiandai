package cn.p2p.p2p.prd.mobile.method.vo;

import java.io.Serializable;

public class BorrowCreditDetailVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String successBorrow;
	private String loseRayment;
	private String successRayment;
	private String advanceRayment;
	private String overdueRaymentLittle;
	private String overdueRaymentmore;
	private String overdueRaymentNo;
	public String getSuccessBorrow() {
		return successBorrow;
	}
	public void setSuccessBorrow(String successBorrow) {
		this.successBorrow = successBorrow;
	}
	public String getLoseRayment() {
		return loseRayment;
	}
	public void setLoseRayment(String loseRayment) {
		this.loseRayment = loseRayment;
	}
	public String getSuccessRayment() {
		return successRayment;
	}
	public void setSuccessRayment(String successRayment) {
		this.successRayment = successRayment;
	}
	public String getAdvanceRayment() {
		return advanceRayment;
	}
	public void setAdvanceRayment(String advanceRayment) {
		this.advanceRayment = advanceRayment;
	}
	public String getOverdueRaymentLittle() {
		return overdueRaymentLittle;
	}
	public void setOverdueRaymentLittle(String overdueRaymentLittle) {
		this.overdueRaymentLittle = overdueRaymentLittle;
	}
	public String getOverdueRaymentmore() {
		return overdueRaymentmore;
	}
	public void setOverdueRaymentmore(String overdueRaymentmore) {
		this.overdueRaymentmore = overdueRaymentmore;
	}
	public String getOverdueRaymentNo() {
		return overdueRaymentNo;
	}
	public void setOverdueRaymentNo(String overdueRaymentNo) {
		this.overdueRaymentNo = overdueRaymentNo;
	}
	
	

}
