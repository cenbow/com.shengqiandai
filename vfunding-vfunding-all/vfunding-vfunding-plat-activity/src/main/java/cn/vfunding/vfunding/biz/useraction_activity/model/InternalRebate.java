package cn.vfunding.vfunding.biz.useraction_activity.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class InternalRebate extends BaseModel {

	// clloctionID
	private Integer cid;

	private Integer userId;
	// 总利息(借款人需支付的总利息)
	private BigDecimal interest;
	// 还款时间
	private Date repayTime;

	private Integer tenderId;

	private BigDecimal account;

	private BigDecimal order;

	public BigDecimal getOrder() {
		return order;
	}

	public void setOrder(BigDecimal order) {
		this.order = order;
	}

	public BigDecimal getAccount() {
		return account;
	}

	public void setAccount(BigDecimal account) {
		this.account = account;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Date getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Date repayTime) {
		this.repayTime = repayTime;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

}
