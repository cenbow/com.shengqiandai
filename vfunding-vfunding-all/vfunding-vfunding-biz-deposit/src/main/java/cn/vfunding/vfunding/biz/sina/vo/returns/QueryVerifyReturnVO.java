package cn.vfunding.vfunding.biz.sina.vo.returns;

/**
 * 2.5	查询认证信息VO
 * @author louchen 2015-01-14
 *
 */
@SuppressWarnings("serial")
public class QueryVerifyReturnVO extends BaseSinaReturnVO {
	private String verify_entity;	//认证内容	String(30)	密文，使用RSA 加密。明文长度：30
	
	private String verify_time;	//认证时间	String(14)	数字串，一共14位
	//格式为：年[4位]月[2位]日[2位]时[2位]分[2位]秒[2位] 
	
	private String extend_param;	//扩展信息	String(200)	业务扩展信息，
	//参数格式：参数名1^参数值1|参数名2^参数值2|……

	public String getVerify_entity() {
		return verify_entity;
	}

	public void setVerify_entity(String verify_entity) {
		this.verify_entity = verify_entity;
	}

	public String getVerify_time() {
		return verify_time;
	}

	public void setVerify_time(String verify_time) {
		this.verify_time = verify_time;
	}

	public String getExtend_param() {
		return extend_param;
	}

	public void setExtend_param(String extend_param) {
		this.extend_param = extend_param;
	}
	
	

}
