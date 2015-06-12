package cn.p2p.p2p.prd.mobile.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

/**
 * 充值推进vo
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class RechargeMobileaAdvanceVO extends BaseVO {
	
	@RestAttribute(len = 0, name = "用户ID", remark = "用户ID", notnull = true)
	private Integer userId;
	
	@RestAttribute(len = 0, name = "用户密码", remark = "用户密码", notnull = true)
	private String password;
	
	@RestAttribute(len = 0, name = "新浪 ticket", remark = "新浪 ticket", notnull = true)
	private String ticket;
	
	@RestAttribute(len = 0, name = "充值流水号", remark = "充值流水号", notnull = true)
	private String outTradeNo;
	
	@RestAttribute(len = 0, name = "充值验证码", remark = "充值验证码", notnull = true)
	private String captcha;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}
	
	
}
