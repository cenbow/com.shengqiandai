package cn.vfunding.vfunding.biz.sina.vo.sends;

import java.io.UnsupportedEncodingException;

import cn.vfunding.vfunding.biz.sina.util.RsaEncryptUtil;


/**
 * 2.6	绑定银行卡VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class BindingBankCardSendVO extends BaseSinaSendVO {
	private String request_no;	//绑卡请求号	String(32) 	商户网站交易订单号，商户内部保证唯一	非空	20131105154925 
	
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	
	private String identity_type;	//用户标识类型	String(16)	ID的类型，目前只包括UID	非空	UID
	
	private String bank_code;	//银行编号	String(10)	见附录	非空	ICBC,CCB
	
	private String bank_account_no;	//银行卡号	String	密文，使用RSA 加密。明文长度：30	非空	XAIDFJAASDF
	
	private String account_name;	//户名	String	密文，使用RSA 加密。明文长度：50。空则使用实名认证时实名信息	可空	XAIDFJAASDF
	
	private String card_type;	//卡类型	String(10)	见附录	非空	DEBIT
	
	private String card_attribute;	//卡属性	String(10)	见附录	非空	C
	
	private String cert_type;	//证件类型	String(10)	见附录，目前只支持身份证，为空则使用实名认证中的证件信息	可空	IC
	
	private String cert_no;	//证件号码	String(18)	密文，使用RSA 加密。明文长度：30。空则使用实名认证时实名信息	可空	XAIDFJAASDF
	
	private String phone_no;	//银行预留手机号	String(16)	密文，使用RSA 加密。明文长度：20。
	//如认证方式不为空，则要求此信息也不能为空。	可空	XAIDFJAASDF
	
	private String validity_period;	//有效期	String	密文，使用RSA 加密。明文长度：10；信用卡专用，有效期(10/13)，（月份/年份）	可空	XAIDFJAASDF
	
	private String verification_value;	//CVV2	String	密文，使用RSA 加密。明文长度：10；信用卡专用	可空	XAIDFJAASDF
	
	private String province;	//省份	String(128)	省份	非空	上海市，江苏省
	
	private String city;	//城市	String(128)	城市	非空	上海市，南京市
	
	private String bank_branch;	//支行名称	String(255)	银行支行名称	可空	中国农业银行深圳南山支行
	
	private String verify_mode;	//认证方式	String(16)	银行卡真实性认证方式，见附录“卡认证方式”，空则表示不认证	可空	SIGN
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……	可空	test^true|notify_type^sync

	public String getRequest_no() {
		return request_no;
	}

	public void setRequest_no(String request_no) {
		this.request_no = request_no;
	}

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

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_account_no() {
		return bank_account_no;
	}

	public void setBank_account_no(String bank_account_no) throws UnsupportedEncodingException, Exception {
		this.bank_account_no = RsaEncryptUtil.encrypt(bank_account_no);
	}

	public String getAccount_name() {
		return account_name;
	}

	public void setAccount_name(String account_name) throws UnsupportedEncodingException, Exception {
		this.account_name = RsaEncryptUtil.encrypt(account_name);
	}

	public String getCard_type() {
		return card_type;
	}

	public void setCard_type(String card_type) {
		this.card_type = card_type;
	}

	public String getCard_attribute() {
		return card_attribute;
	}

	public void setCard_attribute(String card_attribute) {
		this.card_attribute = card_attribute;
	}

	public String getCert_type() {
		return cert_type;
	}

	public void setCert_type(String cert_type) {
		this.cert_type = cert_type;
	}

	public String getCert_no() {
		return cert_no;
	}

	public void setCert_no(String cert_no) throws UnsupportedEncodingException, Exception {
		this.cert_no = RsaEncryptUtil.encrypt(cert_no);
	}

	public String getPhone_no() {
		return phone_no;
	}

	public void setPhone_no(String phone_no) throws UnsupportedEncodingException, Exception {
		this.phone_no = RsaEncryptUtil.encrypt(phone_no);
	}

	public String getValidity_period() throws UnsupportedEncodingException, Exception {
		return RsaEncryptUtil.encrypt(validity_period);
	}

	public void setValidity_period(String validity_period) {
		this.validity_period = validity_period;
	}

	public String getVerification_value() throws UnsupportedEncodingException, Exception {
		return RsaEncryptUtil.encrypt(verification_value);
	}

	public void setVerification_value(String verification_value) {
		this.verification_value = verification_value;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBank_branch() {
		return bank_branch;
	}

	public void setBank_branch(String bank_branch) {
		this.bank_branch = bank_branch;
	}

	public String getVerify_mode() {
		return verify_mode;
	}

	public void setVerify_mode(String verify_mode) {
		this.verify_mode = verify_mode;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public BindingBankCardSendVO() {
		super();
		this.setService("binding_bank_card");
		this.setVersion("1.0");
	}

	
	
}
