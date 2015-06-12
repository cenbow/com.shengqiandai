package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.7	托管退款VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class CreateHostingRefundReturnVO extends BaseSinaReturnVO{
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String refund_status;	//退款状态	String(16)	退款状态，详见附录“退款状态”	可空	SUCCESS

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getRefund_status() {
		return refund_status;
	}

	public void setRefund_status(String refund_status) {
		this.refund_status = refund_status;
	}


}
