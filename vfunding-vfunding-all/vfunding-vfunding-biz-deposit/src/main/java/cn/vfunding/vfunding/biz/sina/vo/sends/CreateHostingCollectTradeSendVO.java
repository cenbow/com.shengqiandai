package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.1	创建托管代收交易VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class CreateHostingCollectTradeSendVO extends BaseSinaSendVO {
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String out_trade_code;	//交易码	String(16)	商户网站代收交易业务码，见附录	非空	1001
	
	private String summary;	//摘要	String(64)	交易内容摘要	非空	房贷还款
	
	private String trade_close_time;	//交易关闭时间	String(8)	设置未付款交易的超时时间，一旦超时，该笔交易就会自动被关闭。
	//取值范围：1m～15d。
	//m-分钟，h-小时，d-天
	//不接受小数点，如1.5d，可转换为36h。	可空	1d
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync
	//支付参数
	
	private String payer_id;	//付款用户ID	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	
	private String payer_identity_type;	//标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
	
	private String payer_ip;	//付款用户ip地址	String(32)	用户在商户平台发起支付时候的IP地址，公网IP，不是内网IP
	//用于风控校验	可空	202.114.12.45
	
	private String pay_method;	//支付方式	String(1000)	取值范围：
	//online_bank (网银支付)
	//balance(余额支付)
	//quick_pay（快捷支付）
	//binding_pay（绑定支付）
	//offline_pay（线下支付）
	//格式：支付方式^金额^扩展|支付方式^金额^扩展。扩展信息内容以“，”分割，针对不同支付方式的扩展定义见附录。 	非空	网银：online_bank^260.00^ICBC，DEBIT,C  ICBC是银行代码，C是对公对私，DEBIT是借记贷记

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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getTrade_close_time() {
		return trade_close_time;
	}

	public void setTrade_close_time(String trade_close_time) {
		this.trade_close_time = trade_close_time;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public String getPayer_id() {
		return payer_id;
	}

	public void setPayer_id(String payer_id) {
		this.payer_id = payer_id;
	}

	public String getPayer_identity_type() {
		return payer_identity_type;
	}

	public void setPayer_identity_type(String payer_identity_type) {
		this.payer_identity_type = payer_identity_type;
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

	public CreateHostingCollectTradeSendVO() {
		super();
		this.setService("create_hosting_collect_trade");
		this.setVersion("1.0");
	}


}
