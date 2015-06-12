package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.2	创建单笔托管代付交易VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class CreateSingleHostingPayTradeSendVO extends BaseSinaSendVO {
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String out_trade_code;	//交易码	String(16)	商户网站代收交易业务码，见附录	非空	2001
	
	private String payee_identity_id;	//收款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	10014542
	
	private String payee_identity_type;	//收款人标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
	
	private String account_type;	//收款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
	
	private String amount;	//金额	Number(15,2)	金额	非空	300.00
	
	private String split_list;	//分账信息列表	String(300)	收款信息列表，参见收款信息，参数间用“^”分隔，各条目之间用“|”分隔，备注信息不要包含特殊分隔符	可空	10014542^UID^BASIC ^10014543^UID^ENSURE^1.00^备注1
	
	private String summary;	//摘要	String(64)	交易内容摘要	非空	房贷还款清偿
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	//分账参数
	//参数	参数名称	类型
	//（长度范围）	参数说明	是否可空	样例
	//业务参数
	//参数1	付款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	20131105154925
	//参数2	付款人标识类型	String(16)	ID的类型，包括UID、MEMBER_ID	非空	UID
	//参数3	付款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
	//参数4	收款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	20131105154925
	//参数5	收款人标识类型	String(16)	ID的类型，包括UID、MEMBER_ID	非空	UID
	//参数6	收款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
	//参数7	金额	Number(15,2)	金额	非空	300.00
	//参数8	备注	String(100)	备注信息	可空	还款清偿分润

	
	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOut_trade_code() {
		return out_trade_code;
	}

	public void setOut_trade_code(String out_trade_code) {
		this.out_trade_code = out_trade_code;
	}

	public String getPayee_identity_id() {
		return payee_identity_id;
	}

	public void setPayee_identity_id(String payee_identity_id) {
		this.payee_identity_id = payee_identity_id;
	}

	public String getPayee_identity_type() {
		return payee_identity_type;
	}

	public void setPayee_identity_type(String payee_identity_type) {
		this.payee_identity_type = payee_identity_type;
	}

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getSplit_list() {
		return split_list;
	}

	public void setSplit_list(String split_list) {
		this.split_list = split_list;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public CreateSingleHostingPayTradeSendVO() {
		super();
		this.setService("create_single_hosting_pay_trade");
		this.setVersion("1.0");
	}

	
}
