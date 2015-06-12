package cn.vfunding.vfunding.biz.week.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class HoldingAssetsVO extends BaseVO{
	/**
	 * 持有资产
	 */
	private BigDecimal holdingAssets; //持有资产
	/**
	 * 累计总收益
	 */
	private BigDecimal totalRevenue; //累计总收益
	/**
	 * 待收收益
	 */
	private BigDecimal collectRevenue;//待收收益
	public BigDecimal getHoldingAssets() {
		if(holdingAssets == null){
			return new BigDecimal(0.00);
		}
		return holdingAssets;
	}
	public void setHoldingAssets(BigDecimal holdingAssets) {
		this.holdingAssets = holdingAssets;
	}
	public BigDecimal getTotalRevenue() {
		if(totalRevenue == null){
			return new BigDecimal(0.00);
		}
		return totalRevenue;
	}
	public void setTotalRevenue(BigDecimal totalRevenue) {
		this.totalRevenue = totalRevenue;
	}
	public BigDecimal getCollectRevenue() {
		if(collectRevenue == null){
			return new BigDecimal(0.00);
		}
		return collectRevenue;
	}
	public void setCollectRevenue(BigDecimal collectRevenue) {
		this.collectRevenue = collectRevenue;
	}
	
	
}
