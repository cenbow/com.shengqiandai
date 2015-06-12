package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class ApplyAmountVO extends BaseVO{
	
	private Integer userId;//申请人id
	
	private String borrowArea;
	
	private String type;
	
	private BigDecimal account;
	
	private String remark;;
	
	private String content;
	
	private Integer limit;
	
	private Integer mortgagetypeId;
	
	private String ip;

	
	public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getMortgagetypeId() {
		return mortgagetypeId;
	}

	public void setMortgagetypeId(Integer mortgagetypeId) {
		this.mortgagetypeId = mortgagetypeId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getBorrowArea() {
		return borrowArea;
	}

	public void setBorrowArea(String borrowArea) {
		this.borrowArea = borrowArea;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
