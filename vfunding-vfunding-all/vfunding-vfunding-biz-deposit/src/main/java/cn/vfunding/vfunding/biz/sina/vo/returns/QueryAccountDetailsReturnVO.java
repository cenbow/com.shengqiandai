package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 2.11	查询收支明细VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryAccountDetailsReturnVO extends BaseSinaReturnVO {
//	业务参数
	private String detail_list;	//收支明细列表	String(4000)	详见“收支条目参数”条目按时间倒序排列，每个条目中的参数用“^”分隔，条目与条目之间用“|”分隔。	可空	还款1^20131117020101^-^30^100|还款2^20131117020102^-^40^60
	
	private String page_no;	//页号	String(5)	同请求值	可空	1
	
	private String page_size;	//每页大小	String(5)	每页记录数，同请求值	可空	30
	
	private String total_item;	//总计录数	number(10)	本次查询返回的总记录数	可空	23
	
	private String total_income;	//总收入	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	可空	30.00
	
	private String total_payout;	//总支出	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	可空	30.00

//		收支条目参数
//	参数	参数名称	类型
//	（长度范围）	参数说明	是否可空	样例
//	业务参数
//	参数1	摘要	String(64)	明细摘要（用途）	非空	还款
//	参数2	入账时间	String(14)	数字串，一共14位
//	格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 	非空	20131117020101
//	参数3	加减方向	String(1)	发生方向，加（+）、减（-）	非空	-
//	参数4	发生额	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	非空	30.00
//	参数5	交易后余额	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	非空	100.00
//	参数6	业务类型	String(14)	当账户类型为SAVING_POT时，非空。见附录	可空	

	
	public String getDetail_list() {
		return detail_list;
	}

	public void setDetail_list(String detail_list) {
		this.detail_list = detail_list;
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

	public String getTotal_income() {
		return total_income;
	}

	public void setTotal_income(String total_income) {
		this.total_income = total_income;
	}

	public String getTotal_payout() {
		return total_payout;
	}

	public void setTotal_payout(String total_payout) {
		this.total_payout = total_payout;
	}


	
}
