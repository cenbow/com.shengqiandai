package cn.vfunding.vfunding.prd.www.vo.pay;

/**
 * 国付宝发送请求VO
 * 
 * @author liuyijun
 * 
 */
public class GoPayVO extends PayBaseVO {

	String userType = "1";
	
	String version = "2.1";
	String charset = "1";
	String language = "1";
	String signType = "1";
	String signValue;
	String tranCode = "8888";
	// String merchantID = "0000000643";
	String merchantID = "0000100776";
	String currencyType = "156";
	String merOrderNum;
	String tranAmt;
	String feeAmt;
	String frontMerUrl = "/pay/goPayForntReceive";
	String backgroundMerUrl="/pay/goPayReceive";
	String tranDateTime;
	// String virCardNoIn = "0000000002000000125";
	String virCardNoIn = "0000000002000150356";
	String tranIP;
	String isRepeatSubmit = "1";
	String respCode;
	String msgExt;
	String orderId;
	String gopayOutOrderId;
	String bankCode;
	String tranFinishTime;
	String merRemark1;
	String merRemark2;
	// String verficationCode = "7778881234";
	String verficationCode = "rongtajr1122";
	String gopayServerTime;

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
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

	public String getSignValue() {
		return signValue;
	}

	public void setSignValue(String signValue) {
		this.signValue = signValue;
	}

	public String getTranCode() {
		return tranCode;
	}

	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}

	public String getMerchantID() {
		return merchantID;
	}

	public void setMerchantID(String merchantID) {
		this.merchantID = merchantID;
	}

	public String getMerOrderNum() {
		return merOrderNum;
	}

	public void setMerOrderNum(String merOrderNum) {
		this.merOrderNum = merOrderNum;
	}

	public String getTranAmt() {
		return tranAmt;
	}

	public void setTranAmt(String tranAmt) {
		this.tranAmt = tranAmt;
	}

	public String getFeeAmt() {
		return feeAmt;
	}

	public void setFeeAmt(String feeAmt) {
		this.feeAmt = feeAmt;
	}

	public String getFrontMerUrl() {
		return frontMerUrl;
	}

	public void setFrontMerUrl(String frontMerUrl) {
		this.frontMerUrl = frontMerUrl;
	}

	public String getBackgroundMerUrl() {
		return backgroundMerUrl;
	}

	public void setBackgroundMerUrl(String backgroundMerUrl) {
		this.backgroundMerUrl = backgroundMerUrl;
	}

	public String getTranDateTime() {
		return tranDateTime;
	}

	public void setTranDateTime(String tranDateTime) {
		this.tranDateTime = tranDateTime;
	}

	public String getVirCardNoIn() {
		return virCardNoIn;
	}

	public void setVirCardNoIn(String virCardNoIn) {
		this.virCardNoIn = virCardNoIn;
	}

	public String getTranIP() {
		return tranIP;
	}

	public void setTranIP(String tranIP) {
		this.tranIP = tranIP;
	}

	public String getIsRepeatSubmit() {
		return isRepeatSubmit;
	}

	public void setIsRepeatSubmit(String isRepeatSubmit) {
		this.isRepeatSubmit = isRepeatSubmit;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getMsgExt() {
		return msgExt;
	}

	public void setMsgExt(String msgExt) {
		this.msgExt = msgExt;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getGopayOutOrderId() {
		return gopayOutOrderId;
	}

	public void setGopayOutOrderId(String gopayOutOrderId) {
		this.gopayOutOrderId = gopayOutOrderId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getTranFinishTime() {
		return tranFinishTime;
	}

	public void setTranFinishTime(String tranFinishTime) {
		this.tranFinishTime = tranFinishTime;
	}

	public String getMerRemark1() {
		return merRemark1;
	}

	public void setMerRemark1(String merRemark1) {
		this.merRemark1 = merRemark1;
	}

	public String getMerRemark2() {
		return merRemark2;
	}

	public void setMerRemark2(String merRemark2) {
		this.merRemark2 = merRemark2;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	public String getVerficationCode() {
		return verficationCode;
	}

	public void setVerficationCode(String verficationCode) {
		this.verficationCode = verficationCode;
	}

	public String getGopayServerTime() {
		return gopayServerTime;
	}

	public void setGopayServerTime(String gopayServerTime) {
		this.gopayServerTime = gopayServerTime;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

}
