package cn.vfunding.vfunding.biz.week.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class WeekVO extends BaseVO{
	private Integer id;

	private String name;

	private Integer status;

	private Integer saveStatus;
	// 期限
	private Integer timeLimit;
	// 年利率
	private BigDecimal apr;
	// 单人最小投资
	private Integer singleMin;
	// 单人最大投资
	private Integer singleMax;

	private Integer buyBase;
	//每份价值
	private BigDecimal sharePrice;

	private Integer shareCount;

	private Integer shareYescount;

	private BigDecimal planMoney;

	private BigDecimal realityMoney;

	private Integer holderUser;

	private Integer createUser;

	private Integer verifyUser;

	private String verifyRemark;

	private Date createTime;

	private Date verifyTime;

	private Date saleTime;

	private Date expireTime;

	private Date publishTime;
	
	
	// 平台服务费率
	private BigDecimal platformRate;
	
	// 监管费率
	private BigDecimal guaranteeRate;

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getSaveStatus() {
		return saveStatus;
	}

	public void setSaveStatus(Integer saveStatus) {
		this.saveStatus = saveStatus;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public Integer getSingleMin() {
		return singleMin;
	}

	public void setSingleMin(Integer singleMin) {
		this.singleMin = singleMin;
	}

	public Integer getSingleMax() {
		return singleMax;
	}

	public void setSingleMax(Integer singleMax) {
		this.singleMax = singleMax;
	}

	public Integer getBuyBase() {
		return buyBase;
	}

	public void setBuyBase(Integer buyBase) {
		this.buyBase = buyBase;
	}

	public BigDecimal getSharePrice() {
		return sharePrice;
	}

	public void setSharePrice(BigDecimal sharePrice) {
		this.sharePrice = sharePrice;
	}

	public Integer getShareCount() {
		return shareCount;
	}

	public void setShareCount(Integer shareCount) {
		this.shareCount = shareCount;
	}

	public Integer getShareYescount() {
		return shareYescount;
	}

	public void setShareYescount(Integer shareYescount) {
		this.shareYescount = shareYescount;
	}

	public BigDecimal getPlanMoney() {
		return planMoney;
	}

	public void setPlanMoney(BigDecimal planMoney) {
		this.planMoney = planMoney;
	}

	public BigDecimal getRealityMoney() {
		return realityMoney;
	}

	public void setRealityMoney(BigDecimal realityMoney) {
		this.realityMoney = realityMoney;
	}

	public Integer getHolderUser() {
		return holderUser;
	}

	public void setHolderUser(Integer holderUser) {
		this.holderUser = holderUser;
	}

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Integer getVerifyUser() {
		return verifyUser;
	}

	public void setVerifyUser(Integer verifyUser) {
		this.verifyUser = verifyUser;
	}

	public String getVerifyRemark() {
		return verifyRemark;
	}

	public void setVerifyRemark(String verifyRemark) {
		this.verifyRemark = verifyRemark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}


	public BigDecimal getGuaranteeRate() {
		return guaranteeRate;
	}

	public void setGuaranteeRate(BigDecimal guaranteeRate) {
		this.guaranteeRate = guaranteeRate;
	}

	public BigDecimal getPlatformRate() {
		return platformRate;
	}

	public void setPlatformRate(BigDecimal platformRate) {
		this.platformRate = platformRate;
	}
	
	
	
	

}
