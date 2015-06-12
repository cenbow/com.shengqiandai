package cn.vfunding.vfunding.biz.borrow.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowTender extends  BaseModel{
	private Integer id;

	private Integer siteId;

	private Integer userId;

	private Integer status;

	private Integer borrowId;

	private String money;

	private String account;

	private String repaymentAccount;

	private String interest;

	private String serviceFees;

	private String guaranteeFees;

	private String returnFees;

	private String inviteFees;

	private String repaymentYesaccount;

	private String waitAccount;

	private String waitInterest;

	private String waitServiceFees;

	private String waitGuaranteeFees;

	private String waitReturnFees;

	private String repaymentYesinterest;

	private Integer addtime;

	private String addip;

	private String tendertime;

	private String username;

	private String equalInterest;

	public String getEqualInterest() {
		return equalInterest;
	}

	public void setEqualInterest(String equalInterest) {
		this.equalInterest = equalInterest;
	}

	public String getTendertime() {
		return tendertime;
	}

	public void setTendertime(String tendertime) {
		this.tendertime = tendertime;
	}

	public String getAllUsername() {
		return username;
	}

	public String getUsername() {
		String name = username;
		if (username.length() > 3) {
			name = (username.substring(0, 3) + "***");
		} else {
			name = (username.substring(0, 1) + "***");
		}
		return name;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSiteId() {
		return siteId;
	}

	public void setSiteId(Integer siteId) {
		this.siteId = siteId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money == null ? null : money.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount == null ? null : repaymentAccount.trim();
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest == null ? null : interest.trim();
	}

	public String getServiceFees() {
		return serviceFees;
	}

	public void setServiceFees(String serviceFees) {
		this.serviceFees = serviceFees == null ? null : serviceFees.trim();
	}

	public String getGuaranteeFees() {
		return guaranteeFees;
	}

	public void setGuaranteeFees(String guaranteeFees) {
		this.guaranteeFees = guaranteeFees == null ? null : guaranteeFees.trim();
	}

	public String getReturnFees() {
		return returnFees;
	}

	public void setReturnFees(String returnFees) {
		this.returnFees = returnFees == null ? null : returnFees.trim();
	}

	public String getInviteFees() {
		return inviteFees;
	}

	public void setInviteFees(String inviteFees) {
		this.inviteFees = inviteFees == null ? null : inviteFees.trim();
	}

	public String getRepaymentYesaccount() {
		return repaymentYesaccount;
	}

	public void setRepaymentYesaccount(String repaymentYesaccount) {
		this.repaymentYesaccount = repaymentYesaccount == null ? null : repaymentYesaccount.trim();
	}

	public String getWaitAccount() {
		return waitAccount;
	}

	public void setWaitAccount(String waitAccount) {
		this.waitAccount = waitAccount == null ? null : waitAccount.trim();
	}

	public String getWaitInterest() {
		return waitInterest;
	}

	public void setWaitInterest(String waitInterest) {
		this.waitInterest = waitInterest == null ? null : waitInterest.trim();
	}

	public String getWaitServiceFees() {
		return waitServiceFees;
	}

	public void setWaitServiceFees(String waitServiceFees) {
		this.waitServiceFees = waitServiceFees == null ? null : waitServiceFees.trim();
	}

	public String getWaitGuaranteeFees() {
		return waitGuaranteeFees;
	}

	public void setWaitGuaranteeFees(String waitGuaranteeFees) {
		this.waitGuaranteeFees = waitGuaranteeFees == null ? null : waitGuaranteeFees.trim();
	}

	public String getWaitReturnFees() {
		return waitReturnFees;
	}

	public void setWaitReturnFees(String waitReturnFees) {
		this.waitReturnFees = waitReturnFees == null ? null : waitReturnFees.trim();
	}

	public String getRepaymentYesinterest() {
		return repaymentYesinterest;
	}

	public void setRepaymentYesinterest(String repaymentYesinterest) {
		this.repaymentYesinterest = repaymentYesinterest == null ? null : repaymentYesinterest.trim();
	}

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}
}