package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

/**
 * 用户投资参数VO
 * 
 * @author lilei
 * 
 */
@SuppressWarnings("serial")
public class UserTenderActionResultVO extends BaseVO{


	@RestAttribute(len = 0, name = "投资状态", remark = "1:全额投资,2:部分投资,3:已满标", notnull = false)
	private Integer status;
	/**
	 * 用户投资金额
	 */
	private BigDecimal payMoney;
	/**
	 * 实际投资金额
	 */
	private BigDecimal realPayMoney;
	
	
	
	
	private Integer tenderId;
	
	/**
	 * 用户ID
	 */
	private Integer userId;

	/**
	 * 标的ID
	 */
	private Integer borrowId;
	/**
	 * 上线ID
	 */
	private Integer puserId;
	/**
	 * 用户类型
	 */
	private Integer userType;
	/**
	 * 用户IP
	 */
	private String userip;

	/**
	 * 是否使用加息卡
	 */
	private Integer isUseHikes;
	
	/**
	 * 使用的加息率
	 */
	private BigDecimal UseHikesRate;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public BigDecimal getRealPayMoney() {
		return realPayMoney;
	}

	public void setRealPayMoney(BigDecimal realPayMoney) {
		this.realPayMoney = realPayMoney;
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

	public BigDecimal getUseHikesRate() {
		return UseHikesRate;
	}

	public void setUseHikesRate(BigDecimal useHikesRate) {
		UseHikesRate = useHikesRate;
	}

	/**
	 * 根据UserTenderActionVO创建投资结果对象，主要用于数据的传递
	 * @param vo
	 * @return
	 * 2014年12月30日
	 * liuyijun
	 */
	public static UserTenderActionResultVO bulidByUserTenderActionVO(UserTenderActionVO vo){
		UserTenderActionResultVO resultVO=new UserTenderActionResultVO();
		resultVO.setPayMoney(vo.getPayMoney());
		resultVO.setBorrowId(vo.getBorrowId());
		resultVO.setIsUseHikes(vo.getIsUseHikes());
		resultVO.setPuserId(vo.getPuserId());
		resultVO.setUserId(vo.getUserId());
		resultVO.setUserType(vo.getUserType());
		resultVO.setUserip(vo.getUserip());
		resultVO.setTenderType(vo.getTenderType());
		resultVO.setBuyShare(vo.getBuyShare());
		resultVO.setWeekId(vo.getWeekId());
		return resultVO;
	}
	
	
}
