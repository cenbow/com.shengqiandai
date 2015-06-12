package cn.vfunding.vfunding.biz.sina.vo.sends;


/**
 * 2.5	查询认证信息
 * @author jianwei
 * @date 2015年1月13日
 */
@SuppressWarnings("serial")
public class QueryVerifySendVO extends BaseSinaSendVO{
public QueryVerifySendVO() {
	// TODO Auto-generated constructor stub
	super();
	this.setService("query_verify");
	this.setVersion("1.0");
}
	private String identity_id;	//用户标识信息	String(32)	商户系统用户id(字母或数字)	非空	2000011212
	private String identity_type;	//用户标识类型	String(16)	ID的类型，目前只包括UID	非空	UID
	private String verify_type;	//认证类型	String(100)	见附录	非空	MOBILE
	private String is_mask;	//是否掩码	String(1)	返回认证内容是否掩码显示， 如手机号掩码为13****8。Y-是；N-否。为空默认为Y。	可空	Y
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
	public String getIs_mask() {
		return is_mask;
	}
	public void setIs_mask(String is_mask) {
		this.is_mask = is_mask;
	}
	public String getExtend_param() {
		return extend_param;
	}
	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}

}
