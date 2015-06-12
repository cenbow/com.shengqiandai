package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.user.model.User;

/**
 * 注册VO
 * @author liuyijun
 * 2014年4月9日
 */
/**
 * @author liuyijun 2014年4月16日
 */
@SuppressWarnings("serial")
public class RegisterVO extends BaseVO {

	/**
	 * 用户名
	 */
	protected String userName;
	/**
	 * 密码
	 */
	protected String password;

	/**
	 * 确认密码
	 */
	protected String rePassword;
	/**
	 * 介绍人
	 */
	protected String introducer;
	/**
	 * 网站联盟用户ID
	 */
	protected String unionUserId;

	/**
	 * 广告产品ID
	 */
	protected String productId;
	/**
	 * 手机号码
	 */
	protected String mobile;

	/**
	 * 用户邮箱
	 */
	protected String email;

	/**
	 * 注册验证码
	 */
	protected String vcode;

	/**
	 * 网站联盟里的体验金
	 */
	protected Integer experienceMoney;


	/**
	 * 用户来源
	 */
	protected String fromType;

	protected String thirdSource;

	protected String thirdSn;
	
	
	protected String ip;
	
	protected String refererUrl;
	
	/**
	 * 是否是财经道注册
	 */
	protected boolean isCjd=false;
	
	/**
	 * 注册用户ID，该ID在用户注册成功后生成
	 */
	protected Integer registerUserId;

	public String getFromType() {
		return fromType;
	}

	public void setFromType(String fromType) {
		this.fromType = fromType;
	}

	/**
	 * 错误时返回的url地址
	 * 
	 * @author liuyijun 2014年4月16日
	 */
	protected String errorReturnUrl;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getIntroducer() {
		return introducer;
	}

	public void setIntroducer(String introducer) {
		this.introducer = introducer;
	}

	public String getUnionUserId() {
		return unionUserId;
	}

	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getErrorReturnUrl() {
		return errorReturnUrl;
	}

	public void setErrorReturnUrl(String errorReturnUrl) {
		this.errorReturnUrl = errorReturnUrl;
	}

	public String getRePassword() {
		return rePassword;
	}

	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}


	public Integer getExperienceMoney() {
		return experienceMoney;
	}

	public void setExperienceMoney(Integer experienceMoney) {
		this.experienceMoney = experienceMoney;
	}

	public String getThirdSource() {
		return thirdSource;
	}

	public void setThirdSource(String thirdSource) {
		this.thirdSource = thirdSource;
	}

	public String getThirdSn() {
		return thirdSn;
	}

	public void setThirdSn(String thirdSn) {
		this.thirdSn = thirdSn;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRefererUrl() {
		return refererUrl;
	}

	public void setRefererUrl(String refererUrl) {
		this.refererUrl = refererUrl;
	}

	public Integer getRegisterUserId() {
		return registerUserId;
	}

	public void setRegisterUserId(Integer registerUserId) {
		this.registerUserId = registerUserId;
	}

	public boolean isCjd() {
		return isCjd;
	}

	public void setCjd(boolean isCjd) {
		this.isCjd = isCjd;
	}
	
	
}
