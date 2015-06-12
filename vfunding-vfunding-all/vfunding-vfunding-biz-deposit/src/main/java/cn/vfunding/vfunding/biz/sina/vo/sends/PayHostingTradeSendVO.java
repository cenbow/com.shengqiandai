package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 托管交易支付
 * @author jianwei
 *
 */
@SuppressWarnings("serial")
public class PayHostingTradeSendVO extends BaseSinaSendVO {
	public PayHostingTradeSendVO() {
		super();
		this.setService("pay_hosting_trade");
		this.setVersion("1.0");
	}
	private String out_pay_no;	//支付请求号	String(32) 	商户网站支付订单号，商户内部保证唯一	非空	20131105154925 
	private String outer_trade_no_list; 	//商户网站唯一交易订单号集合	String(500) 	钱包合作商户网站唯一订单号（确保在合作伙伴系统中唯一）。总数不超过十笔	非空	2013112405052132^2013112405052233 
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync
	//支付参数
	private String payer_ip;	//付款用户ip地址	String(32)	用户在商户平台发起支付时候的IP地址，公网IP，不是内网IP
				//用于风控校验	可空	202.114.12.45
	private String pay_method; 	//支付方式	String(1000)	取值范围：
				//	online_bank (网银支付)
				//	balance(余额支付)
				//	quick_pay（快捷支付）
				//	binding_pay（绑定支付）
				//	offline_pay（线下支付）
				//格式：支付方式^金额^扩展|支付方式^金额^扩展。扩展信息内容以“，”f分割，针对不同支付方式的扩展定义见附录。 	非空	
				//网银：online_bank^260.00^ICBC，DEBIT,C  ICBC是银行代码，C是对公对私，DEBIT是借记贷记
	public String getOut_pay_no() {
		return out_pay_no;
	}
	public void setOut_pay_no(String out_pay_no) {
		this.out_pay_no = out_pay_no;
	}
	public String getOuter_trade_no_list() {
		return outer_trade_no_list;
	}
	public void setOuter_trade_no_list(String outer_trade_no_list) {
		this.outer_trade_no_list = outer_trade_no_list;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
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


}
