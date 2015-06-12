package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 3.10	托管充值查询VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryHostingDepositReturnVO extends BaseSinaReturnVO {
//	业务参数
	private String deposit_list;	//充值明细列表	String(4000)	详见“条目参数”条目按时间倒序排列，每个条目中的参数用“^”分隔，条目与条目之间用“|”分隔。	可空	20131117020101^30^ WAIT_PAY^20131117020101^20131117020101
	
	private String page_no;	//页号	String(5)	同请求值	可空	1
	
	private String page_size;	//每页大小	String(5)	每页记录数，同请求值	可空	30
	
	private String total_item;	//总计录数	number(10)	本次查询返回的总记录数	可空	23

//		充值条目参数
//	参数	参数名称	类型
//	（长度范围）	参数说明	是否可空	样例
//	业务参数
//	参数1	充值订单号	String(64)	外部交易号，即out_trade_no	非空	2013112405052323
//	参数2	金额	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	非空	30.00
//	参数3	状态	String(16)	充值状态，详见附录“支付状态”	非空	SUCCESS
//	参数4	创建时间	String(14)	数字串，一共14位
//	格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 	非空	20131117020101
//	参数5	最后修改时间	String(14)	数字串，一共14位
//	格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 	非空	20131117020101

	
	public String getDeposit_list() {
		return deposit_list;
	}

	public void setDeposit_list(String deposit_list) {
		this.deposit_list = deposit_list;
	}

	public String getPage_no() {
		return page_no;
	}

	public void setPage_no(String page_no) {
		this.page_no = page_no;
	}

	public String getPage_size() {
		return page_size;
	}

	public void setPage_size(String page_size) {
		this.page_size = page_size;
	}

	public String getTotal_item() {
		return total_item;
	}

	public void setTotal_item(String total_item) {
		this.total_item = total_item;
	}
	
}
