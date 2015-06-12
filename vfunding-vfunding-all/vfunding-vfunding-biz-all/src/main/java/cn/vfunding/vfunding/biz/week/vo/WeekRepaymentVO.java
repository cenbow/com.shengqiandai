package cn.vfunding.vfunding.biz.week.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 还款列表vo
 * @author louchen 2014-12-9
 *
 */
@SuppressWarnings("serial")
public class WeekRepaymentVO extends BaseVO{
	private Integer repaymentId;
	private Integer weekId;
	private String weekName;
	//期限
	private Integer timeLimit;
	//年利率
	private BigDecimal apr;
	//还款到期时间
	private Date repaymentTime;
	//本金
	private BigDecimal capital;
	//利息
	private BigDecimal interest;
	//还款总额
	private BigDecimal repaymentAccount;
	//状态
	private Integer status;
	public Integer getRepaymentId() {
		return repaymentId;
	}
	public void setRepaymentId(Integer repaymentId) {
		this.repaymentId = repaymentId;
	}
	public Integer getWeekId() {
		return weekId;
	}
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}
	public String getWeekName() {
		return weekName;
	}
	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}
	public Integer getTimeLimit() {
		return timeLimit;
	}
	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}
	public BigDecimal getApr() {
		return apr;
	}
	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}
	public Date getRepaymentTime() {
		return repaymentTime;
	}
	public void setRepaymentTime(Date repaymentTime) {
		this.repaymentTime = repaymentTime;
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
	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}
	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
