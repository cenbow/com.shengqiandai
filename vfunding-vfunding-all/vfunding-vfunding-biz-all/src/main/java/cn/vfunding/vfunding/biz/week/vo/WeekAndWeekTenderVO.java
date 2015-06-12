package cn.vfunding.vfunding.biz.week.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class WeekAndWeekTenderVO extends BaseVO{
	private String weekName;
	private BigDecimal apr;
	private BigDecimal realbuyShare;
	private BigDecimal money;
	private BigDecimal interest;
	private BigDecimal repaymentAccount;
	private Date addTime;
	private Date repaymentTime;

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getRealbuyShare() {
		return realbuyShare;
	}

	public void setRealbuyShare(BigDecimal realbuyShare) {
		this.realbuyShare = realbuyShare;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Date getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

}
