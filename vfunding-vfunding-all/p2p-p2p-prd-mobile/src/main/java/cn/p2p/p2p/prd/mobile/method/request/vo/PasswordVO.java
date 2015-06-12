package cn.p2p.p2p.prd.mobile.method.request.vo;

import cn.p2p.p2p.prd.mobile.method.request.vo.GeneralRequestVO;

public class PasswordVO extends GeneralRequestVO{
	private String oldPassword; //老密码
	private String newPassword; //新密码
	private String rePassword; //确认密码
	private String cardIdCheck;
	private String passwordType; //0 登陆密码 1支付密码
	
	
	
	public String getPasswordType() {
		return passwordType;
	}
	public void setPasswordType(String passwordType) {
		this.passwordType = passwordType;
	}
	public String getCardIdCheck() {
		return cardIdCheck;
	}
	public void setCardIdCheck(String cardIdCheck) {
		this.cardIdCheck = cardIdCheck;
	}
	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getRePassword() {
		return rePassword;
	}
	public void setRePassword(String rePassword) {
		this.rePassword = rePassword;
	}
	
	
}
