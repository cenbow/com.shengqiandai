package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 5.1	存钱罐基金收益率查询
 * @author jianwei
 * @date 2015年1月13日
 */

@SuppressWarnings("serial")
public class QueryFundYieldSendVO extends BaseSinaSendVO{
public QueryFundYieldSendVO() {
	// TODO Auto-generated constructor stub
	super();
	this.setService("query_fund_yield");
	this.setVersion("1.0");
}
	private String fund_code;	//基金代码	String(16)	标准基金代码，例如000330（汇添富）	非空	000330  

	public String getFund_code() {
		return fund_code;
	}

	public void setFund_code(String fund_code) {
		this.fund_code = fund_code;
	}
	
}
