package cn.vfunding.vfunding.biz.sina.vo.returns;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 新浪资金托管 返回 基本参数VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class BaseSinaReturnVO extends BaseVO{
	private String response_time;	//请求时间	String(14)	发起请求时间，格式yyyyMMddhhmmss	非空	20140101120401
	
	private String partner_id;	//合作者身份ID 	String(32) 	签约合作方的钱包唯一用户号。	可空	2000001159940003
	
	private String _input_charset; 	//参数编码字符集	String(10)	商户网站使用的编码格式，如utf-8、gbk、gb2312等。	非空	UTF-8
	
	private String sign; 	//签名	String(256)	参见“签名机制”。	非空	e8qdwl9caset5zugii2r7q0k8ikopxor 
	
	private String sign_type; 	//签名方式	String(10)	签名方式支持DSA、RSA、MD5。	非空	MD5 
	
	private String sign_version;	//签名版本号	Number(5)	签名密钥版本，默认1.0	可空	1.0
	
	private String response_code;	//响应码	String(30)	参见附录	非空	PARTNER_ID_NOT_EXIST
	
	private String response_message;	//响应信息	String(200)	参见附录	可空	合作方Id不存在
	
	private String memo;	//备注	String(1000)	说明信息，与请求中memo内容一致	可空	

	public String getResponse_time() {
		return response_time;
	}

	public void setResponse_time(String response_time) {
		this.response_time = response_time;
	}

	public String getPartner_id() {
		return partner_id;
	}

	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}

	public String get_input_charset() {
		return _input_charset;
	}

	public void set_input_charset(String _input_charset) {
		this._input_charset = _input_charset;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getSign_type() {
		return sign_type;
	}

	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}

	public String getSign_version() {
		return sign_version;
	}

	public void setSign_version(String sign_version) {
		this.sign_version = sign_version;
	}

	public String getResponse_code() {
		return response_code;
	}

	public void setResponse_code(String response_code) {
		this.response_code = response_code;
	}

	public String getResponse_message() {
		return response_message;
	}

	public void setResponse_message(String response_message) {
		this.response_message = response_message;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	@Override
	public String toString() {
		return "BaseSinaReturnVO [response_time=" + response_time
				+ ", partner_id=" + partner_id + ", _input_charset="
				+ _input_charset + ", sign=" + sign + ", sign_type="
				+ sign_type + ", sign_version=" + sign_version
				+ ", response_code=" + response_code + ", response_message="
				+ response_message + ", memo=" + memo + "]";
	}
	
}
