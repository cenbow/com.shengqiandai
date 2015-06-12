package cn.p2p.p2p.prd.mobile.vo;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

public class VerificationCodeVO {

	@RestAttribute(len = 0, name = "用户ID", remark = "用户ID", notnull = true)
	private Integer userId;

	@RestAttribute(len = 0, name = "验证码", remark = "验证码", notnull = true)
	private String code;

	@RestAttribute(len = 0, name = "密码", remark = "更改密码所需传的字段", notnull = true)
	private String password;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
