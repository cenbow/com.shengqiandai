package cn.p2p.p2p.prd.mobile.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

public class LoginedUserInfo extends BaseVO {

	@RestAttribute(len = 0, name = "用户ID", remark = "用户ID", notnull = true)
	private Integer userId;
	@RestAttribute(len = 0, name = "用户名称", remark = "用户名称", notnull = true)
	private String userName;
	@RestAttribute(len = 0, name = "用户密码", remark = "用户密码", notnull = true)
	private String password;
	@RestAttribute(len = 0, name = "用户手机号码", remark = "用户手机号码", notnull = false)
	private String mobile;
	@RestAttribute(len = 0, name = "用户红包", remark = "用户红包", notnull = true)
	private Integer bonuses;
	@RestAttribute(len = 0, name = "用户支付密码", remark = "用户支付密码", notnull = true)
	private String paypassword;
	
	private String token;
	
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Integer getBonuses() {
		return bonuses;
	}
	public void setBonuses(Integer bonuses) {
		this.bonuses = bonuses;
	}
	public String getPaypassword() {
		return paypassword;
	}
	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}
