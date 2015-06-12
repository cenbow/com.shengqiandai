package cn.vfunding.vfunding.biz.sina.vo.sends;

import java.io.UnsupportedEncodingException;

import cn.vfunding.vfunding.biz.sina.util.RsaEncryptUtil;


/**
 * 2.3	绑定认证信息VO
 * @author louchen 2015-01-13
 *
 */
@SuppressWarnings("serial")
public class BindingVerifySendVO extends BaseSinaSendVO{
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	
	private String identity_type;	//用户标识类型	String(16)	ID的类型，目前只包括UID	非空	UID
	
	private String verify_type;	//认证类型	String(20)	见附录	非空	MOBILE
	
	private String verify_entity;	//认证内容	String(30)	密文，使用RSA 加密。明文长度：30	非空	XAIDFJAASDF
	
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

	public String getVerify_type() {
		return verify_type;
	}

	public void setVerify_type(String verify_type) {
		this.verify_type = verify_type;
	}

	public String getVerify_entity() {
		return verify_entity;
	}

	public void setVerify_entity(String verify_entity) throws UnsupportedEncodingException, Exception {
		this.verify_entity = RsaEncryptUtil.encrypt(verify_entity);
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

	public BindingVerifySendVO() {
		super();
		this.setService("binding_verify");
		this.setVersion("1.0");
	}

	
	
}
