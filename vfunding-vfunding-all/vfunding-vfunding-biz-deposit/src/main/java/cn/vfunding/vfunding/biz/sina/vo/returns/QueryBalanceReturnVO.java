package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 2.10	查询余额/基金份额VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryBalanceReturnVO extends BaseSinaReturnVO{
	//业务参数
	private String balance;	//余额/基金份额	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	非空	10.00
	
	private String available_balance;	//可用余额/基金份额	Number(15,2)	单位为：RMB Yuan。精确到小数点后两位。	非空	8.00
	
	private String bonus;	//存钱罐收益	String(100)	参数格式：昨日收益^最近一周收益^最近一月收益^总收益。	可空	20.00^200.00^2000.00

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getAvailable_balance() {
		return available_balance;
	}

	public void setAvailable_balance(String available_balance) {
		this.available_balance = available_balance;
	}

	public String getBonus() {
		return bonus;
	}

	public void setBonus(String bonus) {
		this.bonus = bonus;
	}

	
}
