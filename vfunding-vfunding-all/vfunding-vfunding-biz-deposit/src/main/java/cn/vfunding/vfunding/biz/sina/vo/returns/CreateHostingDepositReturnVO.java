package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.9	托管充值VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class CreateHostingDepositReturnVO extends BaseSinaReturnVO{
//	业务参数
	private String out_trade_no;	//充值订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String deposit_status;	//充值状态	String(16)	充值状态，详见附录“支付状态”	可空	SUCCESS
	
	private String ticket;	//后续推进需要的参数	String(100)	如果支付需要推进则会返回此参数，支付推进时需要带上此参数，ticket有效期为15分钟，只能使用一次	可空	Aaabbbcccdddeee12345

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getDeposit_status() {
		return deposit_status;
	}

	public void setDeposit_status(String deposit_status) {
		this.deposit_status = deposit_status;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}

	
}
