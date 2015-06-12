package cn.vfunding.vfunding.biz.pdfreport.model;

public class UserTenderInfo {

	private Integer userId;

	private String tenderName;

	private Double account;

	private Double interest;

	private String tendertime;

	private String repaymenttime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getTenderName() {
		return tenderName;
	}

	public void setTenderName(String tenderName) {
		this.tenderName = tenderName;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
	}

	public Double getInterest() {
		return interest;
	}

	public void setInterest(Double interest) {
		this.interest = interest;
	}

	

	public String getTendertime() {
		return tendertime;
	}

	public void setTendertime(String tendertime) {
		this.tendertime = tendertime;
	}

	public String getRepaymenttime() {
		return repaymenttime;
	}

	public void setRepaymenttime(String repaymenttime) {
		this.repaymenttime = repaymenttime;
	}

}
