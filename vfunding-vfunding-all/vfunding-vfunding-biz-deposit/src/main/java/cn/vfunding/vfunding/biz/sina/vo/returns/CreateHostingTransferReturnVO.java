package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.13	转账接口VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class CreateHostingTransferReturnVO extends BaseSinaReturnVO {
	
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String trade_status;	//交易状态	String(16)	交易状态，详见附录“交易状态”	可空	SUCCESS

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getTrade_status() {
		return trade_status;
	}

	public void setTrade_status(String trade_status) {
		this.trade_status = trade_status;
	}

}
