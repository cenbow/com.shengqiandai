package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.11	托管提现VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class CreateHostingWithdrawReturnVO extends BaseSinaReturnVO{
	private String out_trade_no;	//提现订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String withdraw_status;	//提现状态	String(16)	支付状态，详见附录“提现状态”	可空	SUCCESS

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getWithdraw_status() {
		return withdraw_status;
	}

	public void setWithdraw_status(String withdraw_status) {
		this.withdraw_status = withdraw_status;
	}

	
}
