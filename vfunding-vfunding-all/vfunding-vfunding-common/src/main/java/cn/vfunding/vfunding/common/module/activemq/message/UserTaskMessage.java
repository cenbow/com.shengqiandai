package cn.vfunding.vfunding.common.module.activemq.message;

@SuppressWarnings("serial")
public class UserTaskMessage extends BaseMessage {

	
	
	public UserTaskMessage() {
		super();
	}

	private Integer userId;


	private Integer taskId;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTaskId() {
		return taskId;
	}

	public void setTaskId(Integer taskId) {
		this.taskId = taskId;
	}

}
