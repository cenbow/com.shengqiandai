package cn.vfunding.vfunding.prd.www.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class LoginVO extends BaseVO{

	/**
	 * 登陆的值(用户名、邮箱、手机号码)
	 * @author liuyijun 
	 * 2014年4月10日
	 */
	private String loginValue;
	/**
	 * 登陆密码
	 * @author liuyijun 
	 * 2014年4月10日
	 */
	private String password;
	
	/**
	 * 验证码
	 * @author liuyijun 
	 * 2014年4月11日
	 */
	private String vcode;
	
	
	/**
	 * 登录成功后跳转地址
	 * @author liuyijun 
	 * 2014年4月11日
	 */
	private String returnUrl;

	public String getLoginValue() {
		return loginValue;
	}

	public void setLoginValue(String loginValue) {
		this.loginValue = loginValue;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getVcode() {
		return vcode;
	}

	public void setVcode(String vcode) {
		this.vcode = vcode;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	
	
}
