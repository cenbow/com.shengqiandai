package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.5	支付结果查询VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryPayResultReturnVO extends BaseSinaReturnVO {
	private String out_pay_no;	//支付订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String pay_status;	//支付状态	String(16)	支付状态，详见附录“支付状态”	可空	SUCCESS

	public String getOut_pay_no() {
		return out_pay_no;
	}

	public void setOut_pay_no(String out_pay_no) {
		this.out_pay_no = out_pay_no;
	}

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

}
