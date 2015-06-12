package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 审核VO
 *
 */
public class VerifyVO extends BaseVO{

	private Integer userId;
	
	private Integer borrowId;
	
	private String verifyRemark;
	
	private String repaymentRemark;
	
	private Integer status;
	
	private String ip;

	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public String getRepaymentRemark() {
		return repaymentRemark;
	}

	public void setRepaymentRemark(String repaymentRemark) {
		this.repaymentRemark = repaymentRemark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
