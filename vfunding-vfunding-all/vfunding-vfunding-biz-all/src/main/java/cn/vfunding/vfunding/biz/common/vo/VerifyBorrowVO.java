package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 后台审核VO
 *
 */
public class VerifyBorrowVO extends BaseVO{

	private Integer borrowId;
	
	private String userName;
	
	private String borrowName;
	
	private BigDecimal account;
	
	private BigDecimal repaymentAccount;
	
	private String repaymentTimeStr;
	
	private BigDecimal accountYes;
	
	private BigDecimal interest;
	
	private BigDecimal apr;
	
	private BigDecimal aprStr;//净收益率
	
	private Integer tenderTimes;
	
	private Integer timeLimit;
	
	private String addtime;
	
	private String verifyTime;
	
	private Integer status;
	
	private Integer type;
	
	private String biaoType;

	private Integer mortgageTypeid;
	
	
	public String getRepaymentTimeStr() {
		return repaymentTimeStr;
	}

	public void setRepaymentTimeStr(String repaymentTimeStr) {
		this.repaymentTimeStr = repaymentTimeStr;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public Integer getMortgageTypeid() {
		return mortgageTypeid;
	}

	public void setMortgageTypeid(Integer mortgageTypeid) {
		this.mortgageTypeid = mortgageTypeid;
	}

	public BigDecimal getAprStr() {
		return aprStr;
	}

	public void setAprStr(BigDecimal aprStr) {
		this.aprStr = aprStr;
	}

	public String getBiaoType() {
		return biaoType;
	}

	public void setBiaoType(String biaoType) {
		this.biaoType = biaoType;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public Integer getTenderTimes() {
		return tenderTimes;
	}

	public void setTenderTimes(Integer tenderTimes) {
		this.tenderTimes = tenderTimes;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
