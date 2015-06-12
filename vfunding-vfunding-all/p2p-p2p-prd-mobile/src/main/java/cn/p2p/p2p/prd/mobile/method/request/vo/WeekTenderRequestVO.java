package cn.p2p.p2p.prd.mobile.method.request.vo;

import cn.p2p.p2p.biz.mobile.base.model.MobileBaseRequest;

public class WeekTenderRequestVO extends MobileBaseRequest {
	/**
	 * 周盈宝ID
	 */
	private Integer weekId;
	/**
	 * 购买份额
	 */
	private Integer buyShare;
	
	
	private String paypassword;
	
	

	/**
	 * @return the paypassword
	 */
	public String getPaypassword() {
		return paypassword;
	}

	/**
	 * @param paypassword the paypassword to set
	 */
	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}

	/**
	 * @return the weekId
	 */
	public Integer getWeekId() {
		return weekId;
	}

	/**
	 * @param weekId the weekId to set
	 */
	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	/**
	 * @return the buyShare
	 */
	public Integer getBuyShare() {
		return buyShare;
	}

	/**
	 * @param buyShare the buyShare to set
	 */
	public void setBuyShare(Integer buyShare) {
		this.buyShare = buyShare;
	}

	
	
}
