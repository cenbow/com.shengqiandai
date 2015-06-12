package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 语言验证消息
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class SendVoiceCodeMessage extends BaseMessage {

	
	public SendVoiceCodeMessage(){
		super();
	}
	private String mobile;
	private String verifyCode;
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getVerifyCode() {
		return verifyCode;
	}
	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
}
