package cn.vfunding.vfunding.biz.userMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

@SuppressWarnings("serial")
public class UserMobile extends UserBaseMobile {

	@RestAttribute(len = 0, name = "累计收益", remark = "累计收益", notnull = false)
	private BigDecimal sumYield;

	@RestAttribute(len = 0, name = "最近回款时间", remark = "最近回款时间", notnull = false)
	private String endYieldTime;

	@RestAttribute(len = 0, name = "最近回款金额", remark = "最近回款金额", notnull = false)
	private BigDecimal endYieldMoney;

	public BigDecimal getSumYield() {
		return sumYield;
	}

	public void setSumYield(BigDecimal sumYield) {
		this.sumYield = sumYield;
	}

	public String getEndYieldTime() {
		return endYieldTime;
	}

	public void setEndYieldTime(String endYieldTime) {
		this.endYieldTime = endYieldTime;
	}

	public BigDecimal getEndYieldMoney() {
		return endYieldMoney;
	}

	public void setEndYieldMoney(BigDecimal endYieldMoney) {
		this.endYieldMoney = endYieldMoney;
	}

}