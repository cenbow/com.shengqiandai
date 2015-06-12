package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 2.7	绑定银行卡推进VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class BindingBankCardAdvanceReturnVO extends BaseSinaReturnVO{
	private String card_id;	//卡ID	String(32)	钱包系统卡ID	非空	2000011212
	
	private String is_verified;	//是否已认证	String(1)	银行卡是否已认证，Y：已认证；N：未认证	非空	Y

	public String getCard_id() {
		return card_id;
	}

	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}

	public String getIs_verified() {
		return is_verified;
	}

	public void setIs_verified(String is_verified) {
		this.is_verified = is_verified;
	}

	
}
