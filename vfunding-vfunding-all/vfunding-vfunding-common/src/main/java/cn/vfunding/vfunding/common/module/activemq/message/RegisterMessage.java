package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 用户注册时介绍人添加红包消息对象
 * @author liuyijun
 *
 */
/**
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class RegisterMessage extends BaseMessage {

	public RegisterMessage(){
		super();
	}
	private Integer userId;//介绍人Id
	
	private String registerIp;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getRegisterIp() {
		return registerIp;
	}

	public void setRegisterIp(String registerIp) {
		this.registerIp = registerIp;
	}

	
	
}
