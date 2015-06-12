package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 投资用户待收 VO
 */
public class CollectionTenderUserVO extends BaseVO{

	private Integer id;//borrowCollection ID
	
	private String borrowName; //标名称
	
	private String borrowUser; //借款人
	
	private Integer integral; //借款人积分
	
	private BigDecimal apr; //年利率
	
	private Integer timeLimit; //期限
	
	private Integer tenderBorrowTime; //投标时间
	
	private BigDecimal repaymentAccount; //应收本息
	
	private BigDecimal repayYesaccount;//已收金额
	
	private Integer repayYestime;//已还时间 
	
	private Integer repayTime;//应还时间
	
	private BigDecimal capital;//应收本金
	
	private Integer repaymentTime; //应收日期
	
	private BigDecimal account; //借出金额
	
	private BigDecimal interest;//待收利息
	
	private BigDecimal returnFees;//返利利息
	
	private BigDecimal lateInterest; //逾期利息
	
	private BigDecimal inviteFees;
	
	private Integer lateDays;//逾期天数
	
	private Integer status;// 状态
	
	private Integer order;//顺序
	
	private String statusStr;
	
	
	
	public String getStatusStr() {
		if(status == null){
			return "待审";
		}else if(status == 1){
			return "投资成功";
		}else if(status == 2){
			return "失败";
		}
		return statusStr;
	}
	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}
	public Integer getRepayTime() {
		return repayTime;
	}
	public void setRepayTime(Integer repayTime) {
		this.repayTime = repayTime;
	}
	public BigDecimal getInviteFees() {
		return inviteFees;
	}
	public void setInviteFees(BigDecimal inviteFees) {
		this.inviteFees = inviteFees;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public Integer getIntegral() {
		return integral;
	}

	public void setIntegral(Integer integral) {
		this.integral = integral;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getBorrowUser() {
		return borrowUser;
	}

	public void setBorrowUser(String borrowUser) {
		this.borrowUser = borrowUser;
	}

	public Integer getTenderBorrowTime() {
		return tenderBorrowTime;
	}

	public void setTenderBorrowTime(Integer tenderBorrowTime) {
		this.tenderBorrowTime = tenderBorrowTime;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getRepayYesaccount() {
		return repayYesaccount;
	}

	public void setRepayYesaccount(BigDecimal repayYesaccount) {
		this.repayYesaccount = repayYesaccount;
	}

	public Integer getRepayYestime() {
		return repayYestime;
	}

	public void setRepayYestime(Integer repayYestime) {
		this.repayYestime = repayYestime;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public Integer getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Integer repaymentTime) {
		this.repaymentTime = repaymentTime;
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

	public BigDecimal getReturnFees() {
		return returnFees;
	}

	public void setReturnFees(BigDecimal returnFees) {
		this.returnFees = returnFees;
	}

	public BigDecimal getLateInterest() {
		return lateInterest;
	}
	public void setLateInterest(BigDecimal lateInterest) {
		this.lateInterest = lateInterest;
	}
	public Integer getLateDays() {
		return lateDays;
	}

	public void setLateDays(Integer lateDays) {
		this.lateDays = lateDays;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
