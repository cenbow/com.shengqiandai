package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.5	支付结果查询
 * @author jianwei
 * @date 2015年1月13日
 */

@SuppressWarnings("serial")
public class QueryPayResultSendVO extends BaseSinaSendVO{
public QueryPayResultSendVO() {
	super();
	this.setService("query_pay_result");
	this.setVersion("1.0");
}
	private String out_pay_no;	//支付请求号	String(32) 	商户网站支付订单号，商户内部保证唯一	非空	20131105154925 
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
			//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync
	public String getOut_pay_no() {
		return out_pay_no;
	}
	public void setOut_pay_no(String out_pay_no) {
		this.out_pay_no = out_pay_no;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

}
