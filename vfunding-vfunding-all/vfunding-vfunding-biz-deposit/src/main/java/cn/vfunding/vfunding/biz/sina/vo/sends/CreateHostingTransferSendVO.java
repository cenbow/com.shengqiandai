package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.13	转账接口VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class CreateHostingTransferSendVO extends BaseSinaSendVO {
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String payer_identity_id;	//付款人标识	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	
	private String payer_identity_type;	//付款人标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
	
	private String payer_account_type;	//付款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIS
	
	private String payee_identity_id;	//收款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	10014542
	
	private String payee_identity_type;	//收款人标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
	
	private String payee_account_type;	//收款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
	
	private String amount;	//转账金额	Number(15,2)	金额	非空	10.00
	
	private String summary;	//摘要	String(64)	转账内容摘要	可空	还钱
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getPayer_identity_id() {
		return payer_identity_id;
	}

	public void setPayer_identity_id(String payer_identity_id) {
		this.payer_identity_id = payer_identity_id;
	}

	public String getPayer_identity_type() {
		return payer_identity_type;
	}

	public void setPayer_identity_type(String payer_identity_type) {
		this.payer_identity_type = payer_identity_type;
	}

	public String getPayer_account_type() {
		return payer_account_type;
	}

	public void setPayer_account_type(String payer_account_type) {
		this.payer_account_type = payer_account_type;
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

	public String getPayee_account_type() {
		return payee_account_type;
	}

	public void setPayee_account_type(String payee_account_type) {
		this.payee_account_type = payee_account_type;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public CreateHostingTransferSendVO() {
		super();
		this.setService("create_hosting_transfer");
		this.setVersion("1.0");
	}

	
}
