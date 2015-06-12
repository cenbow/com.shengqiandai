package cn.vfunding.vfunding.biz.returns.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class ReturnFeeData extends BaseModel  {

	private Integer status;

	private BigDecimal returnfee;

	private Integer userId;

	private String repaymentTime;

	private String addtime;

	private String borrowName;

	private String userName;

	private BigDecimal account;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(String repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getReturnfee() {
		return returnfee;
	}

	public void setReturnfee(BigDecimal returnfee) {
		this.returnfee = returnfee;
	}

}
