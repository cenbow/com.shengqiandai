package cn.vfunding.vfunding.common.module.activemq.message.useraction;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.module.activemq.message.BaseMessage;

/**
 * 用户投资消息对象
 * 
 * @author liuyijun
 * 
 */
@SuppressWarnings("serial")
public class UserTenderMessage extends BaseMessage {

	public UserTenderMessage() {
		super();
	}

	/**
	 * 支付密码
	 */
	private String paypassword;
	/**
	 * 用户ID
	 */
	private Integer userId;
	/**
	 * 标的ID
	 */
	private Integer borrowId;
	
	private Integer tenderId;

	/**
	 * 投资金额
	 */
	private BigDecimal payMoney;
	/**
	 * 上级用户ID
	 */
	private Integer puserId;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * IP
	 */
	private String userip;

	/**
	 * 是否使用现金券
	 */
	private Integer isUseVolume;

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getPuserId() {
		return puserId;
	}

	public void setPuserId(Integer puserId) {
		this.puserId = puserId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public Integer getIsUseVolume() {
		return isUseVolume;
	}

	public void setIsUseVolume(Integer isUseVolume) {
		this.isUseVolume = isUseVolume;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	
	
}
