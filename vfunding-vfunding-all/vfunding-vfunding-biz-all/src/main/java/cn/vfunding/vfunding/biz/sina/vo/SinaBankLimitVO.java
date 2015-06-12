package cn.vfunding.vfunding.biz.sina.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.StringUtil;

public class SinaBankLimitVO {

	private String bankName;

	private String isQuick;

	private BigDecimal bindingpayFirstLimit;

	private BigDecimal bindingpaySingleLimit;

	private BigDecimal bindingpayDayLimit;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getIsQuick() {
		return isQuick;
	}

	public void setIsQuick(String isQuick) {
		this.isQuick = isQuick;
	}

	public String getBindingpayFirstLimit() {
		return StringUtil.getFinanceString(bindingpayFirstLimit.doubleValue());
	}

	public void setBindingpayFirstLimit(BigDecimal bindingpayFirstLimit) {
		this.bindingpayFirstLimit = bindingpayFirstLimit;
	}

	public String getBindingpaySingleLimit() {
		return StringUtil.getFinanceString(bindingpaySingleLimit.doubleValue());
	}

	public void setBindingpaySingleLimit(BigDecimal bindingpaySingleLimit) {
		this.bindingpaySingleLimit = bindingpaySingleLimit;
	}

	public String getBindingpayDayLimit() {
		return StringUtil.getFinanceString(bindingpayDayLimit.doubleValue());
	}

	public void setBindingpayDayLimit(BigDecimal bindingpayDayLimit) {
		this.bindingpayDayLimit = bindingpayDayLimit;
	}
	
	

}
