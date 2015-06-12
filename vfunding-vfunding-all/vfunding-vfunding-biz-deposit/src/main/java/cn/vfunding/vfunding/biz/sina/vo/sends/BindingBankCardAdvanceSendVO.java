package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 2.7	绑定银行卡推进VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class BindingBankCardAdvanceSendVO extends BaseSinaSendVO{
	private String ticket;	//绑卡时返回的ticket	String(100)	ticket有效期为15分钟，只能使用一次	非空	Aaabbbcccdddeee12345
	
	private String valid_code;	//短信验证码	String(32)	用户绑卡手机收到的验证码	非空	854612
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValid_code() {
		return valid_code;
	}

	public void setValid_code(String valid_code) {
		this.valid_code = valid_code;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public BindingBankCardAdvanceSendVO() {
		super();
		this.setService("binding_bank_card_advance");
		this.setVersion("1.0");
	}

	
}
