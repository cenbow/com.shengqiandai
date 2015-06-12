package cn.vfunding.vfunding.biz.sina.model;

import java.math.BigDecimal;

public class SinaBank {
    private Integer id;

    private String bankCode;

    private String bankName;

    private String isQuick;
    
    private BigDecimal bindingpayFirstLimit;
    
    private BigDecimal bindingpaySingleLimit;
    
    private BigDecimal bindingpayDayLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode == null ? null : bankCode.trim();
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName == null ? null : bankName.trim();
    }

    public String getIsQuick() {
        return isQuick;
    }

    public void setIsQuick(String isQuick) {
        this.isQuick = isQuick == null ? null : isQuick.trim();
    }

	public BigDecimal getBindingpayFirstLimit() {
		return bindingpayFirstLimit;
	}

	public void setBindingpayFirstLimit(BigDecimal bindingpayFirstLimit) {
		this.bindingpayFirstLimit = bindingpayFirstLimit;
	}

	public BigDecimal getBindingpaySingleLimit() {
		return bindingpaySingleLimit;
	}

	public void setBindingpaySingleLimit(BigDecimal bindingpaySingleLimit) {
		this.bindingpaySingleLimit = bindingpaySingleLimit;
	}

	public BigDecimal getBindingpayDayLimit() {
		return bindingpayDayLimit;
	}

	public void setBindingpayDayLimit(BigDecimal bindingpayDayLimit) {
		this.bindingpayDayLimit = bindingpayDayLimit;
	}
}