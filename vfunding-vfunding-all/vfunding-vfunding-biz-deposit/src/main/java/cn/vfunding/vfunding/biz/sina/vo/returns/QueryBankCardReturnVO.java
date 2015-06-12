package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 2.9	查询银行卡VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryBankCardReturnVO extends BaseSinaReturnVO{
	private String card_list;	//卡列表	String(4000)	详见“卡条目参数”条目按时间倒序排列，每个条目中的参数用“^”分隔，条目与条目之间用“|”分隔。	可空	101^ICBC^4003*****001^**三^ DEBIT ^ C

	public String getCard_list() {
		return card_list;
	}

	public void setCard_list(String card_list) {
		this.card_list = card_list;
	}

//	卡条目参数
//	参数	参数名称	类型
//	（长度范围）	参数说明	是否可空	样例
//	业务参数
//	参数1	卡信息ID	String(32)	Card信息id	非空	101
//	参数2	银行编号	String(10)	见附录	非空	ICBC,CCB
//	参数3	银行卡号	String	掩码信息	非空	4003*****001
//	参数4	户名	String	掩码信息	非空	**三
//	参数5	卡类型	String(10)	见附录	非空	DEBIT
//	参数6	卡属性	String(10)	见附录	非空	C
//	参数7	是否已认证	String(1)	银行卡是否已认证，Y：已认证；N：未认证	非空	Y
//	参数8	创建时间	String(14)	数字串，一共14位
//	格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 	非空	20131117020101
//	参数9	安全卡标识	String(1)	银行卡是否为安全卡，Y：安全卡；N：非安全卡	非空	Y

	
}
