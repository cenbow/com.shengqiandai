package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 发送验证邮件消息
 * 
 * @author liuyijun
 * 
 */
@SuppressWarnings("serial")
public class UserEmpMessage extends BaseMessage {

	public UserEmpMessage() {
		super();
	}

	private Integer userId;//注册用户ID
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	

}
