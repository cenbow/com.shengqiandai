package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 发送短信消息
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class SendVerifyCodeMessage extends BaseMessage {
	
	public SendVerifyCodeMessage(){
		super();
	}

	private String mobile;
	private String content;
	/**
	 * 短信类型,注册、投资等等
	 */
	private String type;
	/**
	 * 验证码信息
	 */
	private String code;
	/**
	 * 短信模板中的参数信息
	 */
	private String[] params;
	
	
	
	
	public String[] getParams() {
		return params;
	}
	public void setParams(String[] params) {
		this.params = params;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
	
}
