package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 用户投资参数VO
 * 
 * @author lilei
 * 
 */
@SuppressWarnings("serial")
public class UserTenderActionVO extends BaseVO {

	private String paypassword;

	private Integer userId;
	

	private Integer borrowId;

	private BigDecimal payMoney;

	private Integer puserId;

	private Integer userType;

	private String userip;

	private Integer isUseHikes;
	
	/**
	 * 投资产品，普通标的或周盈宝
	 */
	private String tenderType="normal";
	
	/**
	 * 周盈宝ID
	 */
	private Integer weekId;
	/**
	 * 购买份额
	 */
	private Integer buyShare;
	
	/**
	 * 最终购买份额
	 */
	private Integer realBuyShare;
	
	
	
	

	public Integer getRealBuyShare() {
		return realBuyShare;
	}

	public void setRealBuyShare(Integer realBuyShare) {
		this.realBuyShare = realBuyShare;
	}

	public String getTenderType() {
		return tenderType;
	}

	public void setTenderType(String tenderType) {
		this.tenderType = tenderType;
	}

	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public Integer getBuyShare() {
		return buyShare;
	}

	public void setBuyShare(Integer buyShare) {
		this.buyShare = buyShare;
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

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
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

	public Integer getIsUseHikes() {
		return isUseHikes;
	}

	public void setIsUseHikes(Integer isUseHikes) {
		this.isUseHikes = isUseHikes;
	}

	

}
