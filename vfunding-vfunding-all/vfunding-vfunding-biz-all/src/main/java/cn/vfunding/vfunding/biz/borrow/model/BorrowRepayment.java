package cn.vfunding.vfunding.biz.borrow.model;

import cn.vfunding.common.framework.utils.beans.DateUtil;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowRepayment extends  BaseModel{
	private Integer id;

	private Integer siteId;

	private Integer status;

	private Integer webstatus;

	private Integer order;

	private Integer borrowId;

	private Integer repaymentTime;

	private Integer repaymentYestime;

	private String repaymentAccount;

	private String repaymentYesaccount;

	private Integer lateDays;

	private String lateInterest;

	private String interest;

	private String capital;

	private String forfeit;

	private String reminderFee;

	private String addtime;

	private String addip;

	private String advanceTime;

	private String borrowName; // 标name

	private String timeLimit; // 标的期数
	
	private String userName;//发标人
	
	private String email;//借款人邮箱
	
	private String phone;//借款人手机
	
	private String ownerName; //借款人
	
	
	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRepaymentTimeStr() {
		return DateUtil.getStringDateByLongDate(repaymentTime.longValue(),
				DateUtil.DATESHOWFORMAT);
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getWebstatus() {
		return webstatus;
	}

	public void setWebstatus(Integer webstatus) {
		this.webstatus = webstatus;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Integer repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public Integer getRepaymentYestime() {
		return repaymentYestime;
	}

	public void setRepaymentYestime(Integer repaymentYestime) {
		this.repaymentYestime = repaymentYestime;
	}

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount == null
				? null
				: repaymentAccount.trim();
	}

	public String getRepaymentYesaccount() {
		return repaymentYesaccount;
	}

	public void setRepaymentYesaccount(String repaymentYesaccount) {
		this.repaymentYesaccount = repaymentYesaccount == null
				? null
				: repaymentYesaccount.trim();
	}

	public Integer getLateDays() {
		return lateDays;
	}

	public void setLateDays(Integer lateDays) {
		this.lateDays = lateDays;
	}

	public String getLateInterest() {
		return lateInterest;
	}

	public void setLateInterest(String lateInterest) {
		this.lateInterest = lateInterest == null ? null : lateInterest.trim();
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest == null ? null : interest.trim();
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital == null ? null : capital.trim();
	}

	public String getForfeit() {
		return forfeit;
	}

	public void setForfeit(String forfeit) {
		this.forfeit = forfeit == null ? null : forfeit.trim();
	}

	public String getReminderFee() {
		return reminderFee;
	}

	public void setReminderFee(String reminderFee) {
		this.reminderFee = reminderFee == null ? null : reminderFee.trim();
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime == null ? null : addtime.trim();
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}

	public String getAdvanceTime() {
		return advanceTime;
	}

	public void setAdvanceTime(String advanceTime) {
		this.advanceTime = advanceTime == null ? null : advanceTime.trim();
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

}