package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class ProfitCalJsonVO extends BaseVO{

	
	private BigDecimal monthCapitalInterest;
	
	private BigDecimal monthInterest;

	public BigDecimal getMonthCapitalInterest() {
		return monthCapitalInterest;
	}

	public void setMonthCapitalInterest(BigDecimal monthCapitalInterest) {
		this.monthCapitalInterest = monthCapitalInterest;
	}

	public BigDecimal getMonthInterest() {
		return monthInterest;
	}

	public void setMonthInterest(BigDecimal monthInterest) {
		this.monthInterest = monthInterest;
	}
}
