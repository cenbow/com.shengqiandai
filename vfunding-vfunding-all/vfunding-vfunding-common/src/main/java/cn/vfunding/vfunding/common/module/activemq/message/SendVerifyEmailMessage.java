package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 发送验证邮件消息
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class SendVerifyEmailMessage extends BaseMessage {

	public SendVerifyEmailMessage(){
		super();
	}

	private String email;
	private String userName;
	private String url;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	
}
