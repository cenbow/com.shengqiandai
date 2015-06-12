package cn.vfunding.plat.pay.vo.sinapay;

import java.io.Serializable;

public class SinaPayReturnInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7214731927566570001L;
	
	
	private String merchantAcctId;
	private String version;
	private String language;
	private String signType;
	
	private String payType;
	
	private String bankId;
	
	private String orderId;
	
	private String orderTime;
	
	private String orderAmount;
	
	private String dealId;
	
	private String bankDealId;
	
	
	private String dealTime;
	
	private Integer payAmount;
	
	private Integer fee;
	
	private String ext1;
	
	private String ext2;
	private Integer payResult;
	
	private String payIp;
	
	private String errCode;
	
	
	private String signMsg;


	public String getMerchantAcctId() {
		return merchantAcctId;
	}


	public void setMerchantAcctId(String merchantAcctId) {
		this.merchantAcctId = merchantAcctId;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}


	public String getLanguage() {
		return language;
	}


	public void setLanguage(String language) {
		this.language = language;
	}


	public String getSignType() {
		return signType;
	}


	public void setSignType(String signType) {
		this.signType = signType;
	}


	public String getPayType() {
		return payType;
	}


	public void setPayType(String payType) {
		this.payType = payType;
	}


	public String getBankId() {
		return bankId;
	}


	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	public String getOrderId() {
		return orderId;
	}


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}


	public String getOrderTime() {
		return orderTime;
	}


	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}


	public String getOrderAmount() {
		return orderAmount;
	}


	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}


	public String getDealId() {
		return dealId;
	}


	public void setDealId(String dealId) {
		this.dealId = dealId;
	}


	public String getBankDealId() {
		return bankDealId;
	}


	public void setBankDealId(String bankDealId) {
		this.bankDealId = bankDealId;
	}


	public String getDealTime() {
		return dealTime;
	}


	public void setDealTime(String dealTime) {
		this.dealTime = dealTime;
	}


	public Integer getPayAmount() {
		return payAmount;
	}


	public void setPayAmount(Integer payAmount) {
		this.payAmount = payAmount;
	}


	public Integer getFee() {
		return fee;
	}


	public void setFee(Integer fee) {
		this.fee = fee;
	}


	public String getExt1() {
		return ext1;
	}


	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}


	public String getExt2() {
		return ext2;
	}


	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}


	public Integer getPayResult() {
		return payResult;
	}


	public void setPayResult(Integer payResult) {
		this.payResult = payResult;
	}


	public String getPayIp() {
		return payIp;
	}


	public void setPayIp(String payIp) {
		this.payIp = payIp;
	}


	public String getErrCode() {
		return errCode;
	}


	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}


	public String getSignMsg() {
		return signMsg;
	}


	public void setSignMsg(String signMsg) {
		this.signMsg = signMsg;
	}


	@Override
	public String toString() {
		return "SinaPayReturnInfo [merchantAcctId=" + merchantAcctId
				+ ", version=" + version + ", language=" + language
				+ ", signType=" + signType + ", payType=" + payType
				+ ", bankId=" + bankId + ", orderId=" + orderId
				+ ", orderTime=" + orderTime + ", orderAmount=" + orderAmount
				+ ", dealId=" + dealId + ", bankDealId=" + bankDealId
				+ ", dealTime=" + dealTime + ", payAmount=" + payAmount
				+ ", fee=" + fee + ", ext1=" + ext1 + ", ext2=" + ext2
				+ ", payResult=" + payResult + ", payIp=" + payIp
				+ ", errCode=" + errCode + ", signMsg=" + signMsg + "]";
	}

	
	
	
	
}
