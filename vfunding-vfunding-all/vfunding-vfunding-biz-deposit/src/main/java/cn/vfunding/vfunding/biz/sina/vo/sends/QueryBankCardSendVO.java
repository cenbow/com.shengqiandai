package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 2.9	查询银行卡
 * @author jianwei
 * @date 2015年1月13日
 */

@SuppressWarnings("serial")
public class QueryBankCardSendVO extends BaseSinaSendVO{
public QueryBankCardSendVO() {
	// TODO Auto-generated constructor stub
	super();
	this.setService("query_bank_card");
	this.setVersion("1.0");
}
	//业务参数
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	private String identity_type;	//用户标识类型	String(16)	ID的类型，目前只包括UID	非空	UID
	private String card_id;	//卡ID	String(32)	钱包系统卡ID，绑卡返回的ID	可空	2000011212
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
					//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync
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
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

}
