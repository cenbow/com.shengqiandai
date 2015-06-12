package cn.vfunding.vfunding.biz.borrowMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowBaseMobile extends BaseModel{

	

	private Integer id;

	@RestAttribute(len = 0, name = "标名称", remark = "标名称", notnull = false)
	private String name;

	@RestAttribute(len = 0, name = "需要借的金额", remark = "需要借的金额", notnull = false)
	private BigDecimal account;

	@RestAttribute(len = 0, name = "已经借到的金额", remark = "已经借到的金额", notnull = false)
	private BigDecimal accountYes;

	@RestAttribute(len = 0, name = "贷款期限", remark = "贷款期限", notnull = false)
	private Double timeLimit;

	@RestAttribute(len = 0, name = "预期收益率", remark = "预期收益率", notnull = false)
	private Double expectApr;

	@RestAttribute(len = 0, name = "天标", remark = "timeLimit=0时,显示此字段", notnull = false)
	private BigDecimal daylimit;

	@RestAttribute(len = 0, name = "是否天标", remark = "0为月标,1为天标", notnull = false)
	private BigDecimal isday;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public BigDecimal getAccountYes() {
		return accountYes;
	}

	public void setAccountYes(BigDecimal accountYes) {
		this.accountYes = accountYes;
	}

	public Double getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Double timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Double getExpectApr() {
		return expectApr;
	}

	public void setExpectApr(Double expectApr) {
		this.expectApr = expectApr;
	}

	public BigDecimal getDaylimit() {
		return daylimit;
	}

	public void setDaylimit(BigDecimal daylimit) {
		this.daylimit = daylimit;
	}

	public BigDecimal getIsday() {
		return isday;
	}

	public void setIsday(BigDecimal isday) {
		this.isday = isday;
	}
	
	
}