package cn.vfunding.vfunding.biz.week.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class WeekRepayment extends BaseModel{
	private Integer id;

	private Integer weekId;

	private Integer status;

	private Integer repaymentUser;

	private BigDecimal repaymentAccount;

	private BigDecimal capital;

	private BigDecimal interest;

	private Date repaymentTime;

	private Date repaymentYestime;

	private Date addTime;

	private String addIp;

	private String weekName;

	// 总金额
	private BigDecimal amountMoney;

	// 资金入账账户名
	private String holderUserName;
	// 付款账户名
	private String repaymentUserName;

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public BigDecimal getAmountMoney() {
		return amountMoney;
	}

	public void setAmountMoney(BigDecimal amountMoney) {
		this.amountMoney = amountMoney;
	}

	public String getHolderUserName() {
		return holderUserName;
	}

	public void setHolderUserName(String holderUserName) {
		this.holderUserName = holderUserName;
	}

	public String getRepaymentUserName() {
		return repaymentUserName;
	}

	public void setRepaymentUserName(String repaymentUserName) {
		this.repaymentUserName = repaymentUserName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getRepaymentUser() {
		return repaymentUser;
	}

	public void setRepaymentUser(Integer repaymentUser) {
		this.repaymentUser = repaymentUser;
	}

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Date getRepaymentTime() {
		return repaymentTime;
	}

	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
	}

	public Date getRepaymentYestime() {
		return repaymentYestime;
	}

	public void setRepaymentYestime(Date repaymentYestime) {
		this.repaymentYestime = repaymentYestime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public String getAddIp() {
		return addIp;
	}

	public void setAddIp(String addIp) {
		this.addIp = addIp == null ? null : addIp.trim();
	}
}