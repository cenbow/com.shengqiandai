package cn.vfunding.vfunding.biz.userMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

@SuppressWarnings("serial")
public class UserStatisticsDataMobile extends UserBaseMobile {

	private Integer status;

	@RestAttribute(len = 0, name = "待收总额", remark = "代收总额", notnull = false)
	private BigDecimal sumWaitAccount;

	@RestAttribute(len = 0, name = "待收利息", remark = "待收利息", notnull = false)
	private BigDecimal sumWaitInterest;

	@RestAttribute(len = 0, name = "已赚利息", remark = "已赚利息", notnull = false)
	private BigDecimal sumEarnedInterest;

	@RestAttribute(len = 0, name = "待收好友返利", remark = "待收好友返利", notnull = false)
	private BigDecimal sumWaitSonInterest;

	@RestAttribute(len = 0, name = "已赚好友返利", remark = "已赚好友返利", notnull = false)
	private BigDecimal sumEarnedSonInterest;

	@RestAttribute(len = 0, name = "累计充值", remark = "累计充值", notnull = false)
	private BigDecimal sumRechargeMoney;

	@RestAttribute(len = 0, name = "累计投资", remark = "累计投资", notnull = false)
	private BigDecimal sumTenderMoney;

	@RestAttribute(len = 0, name = "累计提现", remark = "累计提现", notnull = false)
	private BigDecimal sumCashMoney;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getSumWaitAccount() {
		return sumWaitAccount;
	}

	public void setSumWaitAccount(BigDecimal sumWaitAccount) {
		this.sumWaitAccount = sumWaitAccount;
	}

	public BigDecimal getSumWaitInterest() {
		return sumWaitInterest;
	}

	public void setSumWaitInterest(BigDecimal sumWaitInterest) {
		this.sumWaitInterest = sumWaitInterest;
	}

	public BigDecimal getSumEarnedInterest() {
		return sumEarnedInterest;
	}

	public void setSumEarnedInterest(BigDecimal sumEarnedInterest) {
		this.sumEarnedInterest = sumEarnedInterest;
	}

	public BigDecimal getSumWaitSonInterest() {
		return sumWaitSonInterest;
	}

	public void setSumWaitSonInterest(BigDecimal sumWaitSonInterest) {
		this.sumWaitSonInterest = sumWaitSonInterest;
	}

	public BigDecimal getSumEarnedSonInterest() {
		return sumEarnedSonInterest;
	}

	public void setSumEarnedSonInterest(BigDecimal sumEarnedSonInterest) {
		this.sumEarnedSonInterest = sumEarnedSonInterest;
	}

	public BigDecimal getSumRechargeMoney() {
		return sumRechargeMoney;
	}

	public void setSumRechargeMoney(BigDecimal sumRechargeMoney) {
		this.sumRechargeMoney = sumRechargeMoney;
	}

	public BigDecimal getSumTenderMoney() {
		return sumTenderMoney;
	}

	public void setSumTenderMoney(BigDecimal sumTenderMoney) {
		this.sumTenderMoney = sumTenderMoney;
	}

	public BigDecimal getSumCashMoney() {
		return sumCashMoney;
	}

	public void setSumCashMoney(BigDecimal sumCashMoney) {
		this.sumCashMoney = sumCashMoney;
	}
}
