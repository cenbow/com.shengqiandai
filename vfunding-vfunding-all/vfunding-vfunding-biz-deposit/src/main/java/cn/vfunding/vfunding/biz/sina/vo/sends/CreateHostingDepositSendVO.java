package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.9	托管充值VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class CreateHostingDepositSendVO extends BaseSinaSendVO{
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	
	private String identity_type;	//用户标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
	
	private String account_type;	//账户类型	String(16)	账户类型（基本户、保证金户,存钱罐）。默认基本户，见附录	可空	BASIC
	
	private String amount;	//金额	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	非空	25.00
	
	private String payer_ip;	//付款用户ip地址	String(32)	用户在商户平台发起支付时候的IP地址，公网IP，不是内网IP
	//用于风控校验	可空	202.114.12.45
	
	private String pay_method; 	//支付方式	String(1000)	取值范围：
	//online_bank (网银支付)
	//quick_pay（快捷）
	//binding_pay（绑定支付）
	//格式：支付方式^金额^扩展|支付方式^金额^扩展。扩展信息内容以“，”f分割，针对不同支付方式的扩展定义见附录。 	非空	网银：online_bank^260.00^ICBC，DEBIT,C  ICBC是银行代码，C是对公对私，DEBIT是借记贷记

	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getIdentity_id() {
		return identity_id;
	}

	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}

	public String getIdentity_type() {
		return identity_type;
	}

	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
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

	public String getPayer_ip() {
		return payer_ip;
	}

	public void setPayer_ip(String payer_ip) {
		this.payer_ip = payer_ip;
	}

	public String getPay_method() {
		return pay_method;
	}

	public void setPay_method(String pay_method) {
		this.pay_method = pay_method;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public CreateHostingDepositSendVO() {
		super();
		this.setService("create_hosting_deposit");
		this.setVersion("1.0");
	}

	@Override
	public String toString() {
		return "CreateHostingDepositSendVO [out_trade_no=" + out_trade_no
				+ ", identity_id=" + identity_id + ", identity_type="
				+ identity_type + ", account_type=" + account_type
				+ ", amount=" + amount + ", payer_ip=" + payer_ip
				+ ", pay_method=" + pay_method + ", extend_param="
				+ extend_param + "]"
				+ super.toString();
	}

	
	
}
