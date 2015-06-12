package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.1	创建托管代收交易VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class CreateHostingCollectTradeReturnVO extends BaseSinaReturnVO{
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String trade_status;	//交易状态	String(16)	交易状态，详见附录“交易状态”	可空	SUCCESS
	
	private String pay_status;	//支付状态	String(16)	支付状态，详见附录“支付状态”	可空	SUCCESS
	
	private String ticket;	//后续推进需要的参数	String(100)	如果支付需要推进则会返回此参数，支付推进时需要带上此参数，ticket有效期为15分钟，只能使用一次	可空	Aaabbbcccdddeee12345

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

	public String getPay_status() {
		return pay_status;
	}

	public void setPay_status(String pay_status) {
		this.pay_status = pay_status;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	
}
