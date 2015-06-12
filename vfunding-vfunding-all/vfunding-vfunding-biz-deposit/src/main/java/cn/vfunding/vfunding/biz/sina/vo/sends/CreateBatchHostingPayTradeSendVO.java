package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.3	创建批量托管代付交易VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class CreateBatchHostingPayTradeSendVO extends BaseSinaSendVO {
	private String out_pay_no;	//支付请求号	String(32) 	商户网站支付订单号，商户内部保证唯一	非空	20131105154925 
	
	private String out_trade_code;	//交易码	String(16)	商户网站代收交易业务码，见附录	非空	2001
	
	private String trade_list;	//交易列表	无长度范围限制	详见“交易参数”。参数间用“~”分隔，各条目之间用“$”分隔，备注信息不要包含特殊分隔符	非空	20131105154925~10014542~UID~1.00~BASIC~~~$20131105154926~10014543~UID~1.00~BASIC~~测试~
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	//交易参数
//	参数	参数名称	类型
//	（长度范围）	参数说明	是否可空	样例
//	业务参数
//	参数1	交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
//	参数2	收款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	10014542
//	参数3	标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
//	参数4	账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
//	参数5	金额	Number(15,2)	金额	非空	300.00
//	参数6	分账信息列表	String(300)	收款信息列表，参见收款信息，参数间用“^”分隔，各条目之间用“|”分隔，备注信息不要包含特殊分隔符	可空	10014542^UID^BASIC ^10014543^UID^ENSURE^1.00^备注1
//	参数7	摘要	String(64)	交易内容摘要	非空	房贷还款清偿
//	参数8	扩展信息	String(200)	业务扩展信息，
//	参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync
	
	//分账参数
//	参数	参数名称	类型
//	（长度范围）	参数说明	是否可空	样例
//	业务参数
//	参数1	付款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	20131105154925
//	参数2	付款人标识类型	String(16)	ID的类型，包括UID、MEMBER_ID	非空	UID
//	参数3	付款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
//	参数4	收款人标识	String(32)	商户系统用户ID、钱包系统会员ID	非空	20131105154925
//	参数5	收款人标识类型	String(16)	ID的类型，包括UID、MEMBER_ID	非空	UID
//	参数6	收款人账户类型	String(16)	账户类型（基本户、保证金户）。默认基本户，见附录	可空	BASIC
//	参数7	金额	Number(15,2)	金额	非空	300.00
//	参数8	备注	String(100)	备注信息	可空	还款清偿分润

	public String getOut_pay_no() {
		return out_pay_no;
	}

	public void setOut_pay_no(String out_pay_no) {
		this.out_pay_no = out_pay_no;
	}

	public String getOut_trade_code() {
		return out_trade_code;
	}

	public void setOut_trade_code(String out_trade_code) {
		this.out_trade_code = out_trade_code;
	}

	public String getTrade_list() {
		return trade_list;
	}

	public void setTrade_list(String trade_list) {
		this.trade_list = trade_list;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public CreateBatchHostingPayTradeSendVO() {
		super();
		this.setService("create_batch_hosting_pay_trade");
		this.setVersion("1.0");
	}
	
	
}
