package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.14	支付推进VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class AdvanceHostingPaySendVO extends BaseSinaSendVO{
	private String out_advance_no;	//支付推进请求号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String ticket;	//支付时返回的ticket	String(100)	ticket有效期为15分钟，只能使用一次	非空	Aaabbbcccdddeee12345
	
	private String validate_code;	//短信验证码	String(12)	银行短信验证码	非空	123456
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	public String getOut_advance_no() {
		return out_advance_no;
	}

	public void setOut_advance_no(String out_advance_no) {
		this.out_advance_no = out_advance_no;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	public String getValidate_code() {
		return validate_code;
	}

	public void setValidate_code(String validate_code) {
		this.validate_code = validate_code;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public AdvanceHostingPaySendVO() {
		super();
		this.setService("advance_hosting_pay");
		this.setVersion("1.0");
	}

	
}
