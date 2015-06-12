package cn.vfunding.vfunding.biz.sina.util;

import cn.vfunding.vfunding.biz.sina.tools.SinaPropertiesUtil;

/**
 * 读取sina配置属性的工具类
 * @author louchen 2015-01-14
 *
 */
public class SinaParamsUtil {
	private String membersUrl;
	
	private String tradesUrl;
	
	private String partnerId;
	
	private String signType;
	
	private String inputCharset;
	
	private String keyMD5;
	
	private String keyRSA;
	private String keyRSAparm;
	
	private String returnUrlHost;
	
	private String notifyUrlHost;
	
	private String vfundingCompanyAccount;
	
	private String fundCode;
	
	private static SinaParamsUtil instance = null;

	private SinaParamsUtil() {
	}

	private static synchronized void syncInit() {
		if (instance == null) {
			instance = new SinaParamsUtil();
			instance.setFundCode(SinaPropertiesUtil.getValue("sina.fundCode")); 
			instance.setMembersUrl(SinaPropertiesUtil.getValue("sina.members_url")); 
			instance.setTradesUrl(SinaPropertiesUtil.getValue("sina.trades_url"));
			instance.setPartnerId(SinaPropertiesUtil.getValue("sina.partnerId"));
			instance.setSignType(SinaPropertiesUtil.getValue("sina.signType"));
			instance.setInputCharset(SinaPropertiesUtil.getValue("sina.inputCharset"));
			instance.setKeyMD5(SinaPropertiesUtil.getValue("sina.MD5"));
			instance.setReturnUrlHost(SinaPropertiesUtil.getValue("sina.returnUrlHost"));
			instance.setNotifyUrlHost(SinaPropertiesUtil.getValue("sina.notifyUrlHost"));
			instance.setVfundingCompanyAccount(SinaPropertiesUtil.getValue("sina.vfundingCompanyAccount"));
			instance.setKeyRSAparm(SinaPropertiesUtil.getValue("sina.RSAparm"));
		}
	}

	public static SinaParamsUtil getInstance() {
		if (instance == null) {
			syncInit();
		}
		return instance;
	}

	public String getMembersUrl() {
		return membersUrl;
	}

	public void setMembersUrl(String membersUrl) {
		this.membersUrl = membersUrl;
	}

	public String getTradesUrl() {
		return tradesUrl;
	}

	public void setTradesUrl(String tradesUrl) {
		this.tradesUrl = tradesUrl;
	}

	public String getPartnerId() {
		return partnerId;
	}

	public String getKeyRSAparm() {
		return keyRSAparm;
	}

	public void setKeyRSAparm(String keyRSAparm) {
		this.keyRSAparm = keyRSAparm;
	}

	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public String getInputCharset() {
		return inputCharset;
	}

	public void setInputCharset(String inputCharset) {
		this.inputCharset = inputCharset;
	}

	public String getKeyMD5() {
		return keyMD5;
	}

	public void setKeyMD5(String keyMD5) {
		this.keyMD5 = keyMD5;
	}

	public String getKeyRSA() {
		return keyRSA;
	}

	public void setKeyRSA(String keyRSA) {
		this.keyRSA = keyRSA;
	}
	
	public String getReturnUrlHost() {
		return returnUrlHost;
	}

	public void setReturnUrlHost(String returnUrlHost) {
		this.returnUrlHost = returnUrlHost;
	}

	public String getNotifyUrlHost() {
		return notifyUrlHost;
	}

	public void setNotifyUrlHost(String notifyUrlHost) {
		this.notifyUrlHost = notifyUrlHost;
	}
	
	/**
	 * 微积金商户账号,收发钱用
	 * @return
	 */
	public String getVfundingCompanyAccount() {
		return vfundingCompanyAccount;
	}

	public void setVfundingCompanyAccount(String vfundingCompanyAccount) {
		this.vfundingCompanyAccount = vfundingCompanyAccount;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}
	
}
