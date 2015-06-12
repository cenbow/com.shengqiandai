package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 3.12	托管提现查询
 * @author jianwei
 * @date 2015年1月13日
 */

@SuppressWarnings("serial")
public class QueryHostingWithdrawSendVO extends BaseSinaSendVO{
public QueryHostingWithdrawSendVO() {
	super();
	this.setService("query_hosting_withdraw");
	this.setVersion("1.0");
}
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	private String identity_type;	//用户标识类型	String(16)	ID的类型，包括UID、MOBILE、EMAIL	非空	UID
	private String account_type;	//账户类型	String(16)	账户类型（基本户、存钱罐）。默认基本户，见附录	可空	BASIC
	private String out_trade_no;	//交易订单号	String(32) 	商户网站交易订单号，商户内部保证唯一	可空	20131105154925 
	private String start_time;	//开始时间	String(14)	数字串，一共14位
								//格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 	可空	20131117020101
	private String end_time;	//结束时间	String(14)	数字串，一共14位
								//格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 	可空	20131117020101
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
								//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync
	private Integer page_no;	//页号	Number(5)	页号，从1开始，默认为1	可空	1
	private Integer page_size;	//每页大小	Number(5)	每页记录数，默认20	可空	30
	public String getIdentity_id() {
		return identity_id;
	}
	public void setIdentity_id(String identity_id) {
		this.identity_id = identity_id;
	}
	public String getIdentity_type() {
		return identity_type;
	}
	public void setIdentity_type(String identity_type) {
		this.identity_type = identity_type;
	}
	public String getAccount_type() {
		return account_type;
	}
	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_time() {
		return end_time;
	}
	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}
	public Integer getPage_no() {
		return page_no;
	}
	public void setPage_no(Integer page_no) {
		this.page_no = page_no;
	}
	public Integer getPage_size() {
		return page_size;
	}
	public void setPage_size(Integer page_size) {
		this.page_size = page_size;
	}

}
