package cn.vfunding.vfunding.common.module.activemq.message;

/**
 * 用户最后一次请求时间记录消息
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class LastRequestTimeMessage extends BaseMessage {

	public LastRequestTimeMessage(){
		super();
	}
	private Integer userId;
	
	private String sessionId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
	
	

}
