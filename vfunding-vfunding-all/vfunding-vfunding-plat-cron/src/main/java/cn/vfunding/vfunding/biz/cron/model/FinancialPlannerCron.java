package cn.vfunding.vfunding.biz.cron.model;

public class FinancialPlannerCron {

	private Integer userId;

	private Integer typeId;

	private Float userMoney;

	private Float userSonMoney;

	private Integer userInviteCount;

	private Integer theNumber;

	private Integer days;

	private Integer userStatus;

	private String addTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Float getUserMoney() {
		if (userMoney == null) {
			userMoney = 0f;
		}
		return userMoney;
	}

	public void setUserMoney(Float userMoney) {
		this.userMoney = userMoney;
	}

	public Float getUserSonMoney() {
		if (userSonMoney == null) {
			userSonMoney = 0f;
		}
		return userSonMoney;
	}

	public void setUserSonMoney(Float userSonMoney) {
		this.userSonMoney = userSonMoney;
	}

	public Integer getUserInviteCount() {
		if (userInviteCount == null) {
			userInviteCount = 0;
		}
		return userInviteCount;
	}

	public void setUserInviteCount(Integer userInviteCount) {
		this.userInviteCount = userInviteCount;
	}

	public Integer getTheNumber() {
		if (theNumber == null) {
			theNumber = 0;
		}
		return theNumber;
	}

	public void setTheNumber(Integer theNumber) {
		this.theNumber = theNumber;
	}

	public Integer getUserStatus() {
		if (userStatus == null) {
			userStatus = 0;
		}
		return userStatus;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public String getAddTime() {
		return addTime;
	}

	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}

	public Integer getDays() {
		if (days == null) {
			days = 0;
		}
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

}
