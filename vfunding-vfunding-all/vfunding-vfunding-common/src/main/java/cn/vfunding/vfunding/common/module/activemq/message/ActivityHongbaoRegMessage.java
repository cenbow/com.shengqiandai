package cn.vfunding.vfunding.common.module.activemq.message;

@SuppressWarnings("serial")
public class ActivityHongbaoRegMessage extends BaseMessage {

	public ActivityHongbaoRegMessage() {
		super();
	}

	private Integer userId;

	private String phone;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
