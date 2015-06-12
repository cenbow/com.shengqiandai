package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.7	托管退款VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class CreateHostingRefundSendVO extends BaseSinaSendVO{
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String orig_outer_trade_no; 	//原始的商户网站唯一交易订单号	String(32) 	原始的钱包合作商户网站唯一订单号（确保在合作伙伴系统中唯一）。同交易中的一致。	非空	2013112405052323
	
	private String refund_amount;	//退款金额	Number(15,2) 	支持部分退款，退款金额不能大于交易金额。
	//单位为：RMB Yuan，精确到小数点后两位。	非空	50.00
	
	private String summary;	//摘要	String(64)	退款原因	可空	流标退款
	
	private String split_list;	//分账信息列表	String(1000)	收款信息列表，参见收款信息，参数间用“^”分隔，各条目之间用“|”分隔，备注信息不要包含特殊分隔符	可空	付款人标识1^付款人标识类型1^收款人标识1^收款人标识类型1^金额1^备注1
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getOrig_outer_trade_no() {
		return orig_outer_trade_no;
	}

	public void setOrig_outer_trade_no(String orig_outer_trade_no) {
		this.orig_outer_trade_no = orig_outer_trade_no;
	}

	public String getRefund_amount() {
		return refund_amount;
	}

	public void setRefund_amount(String refund_amount) {
		this.refund_amount = refund_amount;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getSplit_list() {
		return split_list;
	}

	public void setSplit_list(String split_list) {
		this.split_list = split_list;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public CreateHostingRefundSendVO() {
		super();
		this.setService("create_hosting_refund");
		this.setVersion("1.0");
	}
	
	

}
