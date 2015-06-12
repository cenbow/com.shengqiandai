package cn.vfunding.vfunding.biz.sina.vo.sends;

import java.io.UnsupportedEncodingException;

import cn.vfunding.vfunding.biz.sina.util.RsaEncryptUtil;


/**
 * 2.2	设置实名信息
 * @author jianwei
 * @date 2015年1月13日
 */

@SuppressWarnings("serial")
public class SetRealNameSendVO extends BaseSinaSendVO{
public SetRealNameSendVO() {
	// TODO Auto-generated constructor stub
	super();
	this.setService("set_real_name");
	this.setVersion("1.0");
}
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	private String identity_type;	//用户标识类型	String(16)	ID的类型，目前只包括UID	非空	UID
	private String real_name;	//真实姓名	String(50)	密文，使用RSA 加密。明文长度：50	非空	XAIDFJAASDF
	private String cert_type;	//证件类型	String(18)	见附录，目前只支持身份证	非空	IC
	private String cert_no;//证件号码	String(18)	密文，使用RSA 加密。明文长度：30	非空	XAIDFJAASDF
	private String need_confirm;//	是否认证	String(1)	是否需要钱包做实名认证，值为Y/N，默认Y。暂不开放外部自助实名认证。	可空	Y
	private String extend_param;//	扩展信息	String(200)	业务扩展信息，
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
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) throws UnsupportedEncodingException, Exception {
		this.real_name = RsaEncryptUtil.encrypt(real_name);
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
	public String getNeed_confirm() {
		return need_confirm;
	}
	public void setNeed_confirm(String need_confirm) {
		this.need_confirm = need_confirm;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

}
