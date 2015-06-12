package cn.vfunding.vfunding.common.module.activemq.message;

public class UserFromMessage extends BaseMessage {


	public UserFromMessage() {
		super();
	}

	private Integer userId;

	private String referUrl;
	
	private String thirdSource;
	
	private String thirdSn;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getReferUrl() {
		return referUrl;
	}

	public void setReferUrl(String referUrl) {
		this.referUrl = referUrl;
	}

	public String getThirdSource() {
		return thirdSource;
	}

	public void setThirdSource(String thirdSource) {
		this.thirdSource = thirdSource;
	}

	public String getThirdSn() {
		return thirdSn;
	}

	public void setThirdSn(String thirdSn) {
		this.thirdSn = thirdSn;
	}
}
