package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 发送验证邮件消息
 * 
 * @author liuyijun
 * 
 */
@SuppressWarnings("serial")
public class SendEmailMessage extends BaseMessage {

	public SendEmailMessage(){
		super();
	}
	private String email;
	private String title;
	private String content;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
