package cn.vfunding.vfunding.biz.pdfreport.model;

public class UserPdfInfo {

	private Integer userId;
	private Integer tenderUserId;
	private String userName;
	private String userEmail;
	private Double totalMoney;
	private Double useMoney;
	private Integer phoneStatus;
	private Integer emailStatus;
	private Integer realStatus;
	private Integer vipStatus;
	private Double userHavaInterestMoney;
	private Double userSonMoney;

	private Double allInterest;
	private Double allAccount;

	public Double getAllInterest() {
		return allInterest;
	}

	public void setAllInterest(Double allInterest) {
		this.allInterest = allInterest;
	}

	public Double getAllAccount() {
		return allAccount;
	}

	public void setAllAccount(Double allAccount) {
		this.allAccount = allAccount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenderUserId() {
		return tenderUserId;
	}

	public void setTenderUserId(Integer tenderUserId) {
		this.tenderUserId = tenderUserId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {

		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public Double getTotalMoney() {
		if (totalMoney == null) {
			totalMoney = 0d;
		}
		return totalMoney;
	}

	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public Double getUseMoney() {
		if (useMoney == null) {
			useMoney = 0d;
		}
		return useMoney;
	}

	public void setUseMoney(Double useMoney) {
		this.useMoney = useMoney;
	}

	public Integer getPhoneStatus() {
		if (phoneStatus == null) {
			phoneStatus = 0;
		}
		return phoneStatus;
	}

	public void setPhoneStatus(Integer phoneStatus) {
		this.phoneStatus = phoneStatus;
	}

	public Integer getEmailStatus() {
		if (emailStatus == null) {
			emailStatus = 0;
		}
		return emailStatus;
	}

	public void setEmailStatus(Integer emailStatus) {
		this.emailStatus = emailStatus;
	}

	public Integer getRealStatus() {
		if (realStatus == null) {
			realStatus = 0;
		}
		return realStatus;
	}

	public Integer getVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(Integer vipStatus) {
		this.vipStatus = vipStatus;
	}

	public void setRealStatus(Integer realStatus) {
		this.realStatus = realStatus;
	}

	public Double getUserHavaInterestMoney() {
		if (userHavaInterestMoney == null) {
			userHavaInterestMoney = 0d;
		}
		return userHavaInterestMoney;
	}

	public void setUserHavaInterestMoney(Double userHavaInterestMoney) {
		this.userHavaInterestMoney = userHavaInterestMoney;
	}

	public Double getUserSonMoney() {
		if (userSonMoney == null) {
			userSonMoney = 0d;
		}
		return userSonMoney;
	}

	public void setUserSonMoney(Double userSonMoney) {
		this.userSonMoney = userSonMoney;
	}

}
