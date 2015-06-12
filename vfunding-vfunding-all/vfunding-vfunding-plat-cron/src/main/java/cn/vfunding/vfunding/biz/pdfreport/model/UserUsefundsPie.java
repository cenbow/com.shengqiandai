package cn.vfunding.vfunding.biz.pdfreport.model;

public class UserUsefundsPie {

	private Integer userId;

	private Integer tenderUserId;

	private Double cashFeeMoney;

	private Double userSonMoney;

	private Double userHavaInterestMoney;

	public Integer getTenderUserId() {
		return tenderUserId;
	}

	public void setTenderUserId(Integer tenderUserId) {
		this.tenderUserId = tenderUserId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Double getCashFeeMoney() {
		if (cashFeeMoney == null) {
			cashFeeMoney = 0d;
		}
		return cashFeeMoney;
	}

	public void setCashFeeMoney(Double cashFeeMoney) {
		this.cashFeeMoney = cashFeeMoney;
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

	public Double getUserHavaInterestMoney() {
		if (userHavaInterestMoney == null) {
			userHavaInterestMoney = 0d;
		}
		return userHavaInterestMoney;
	}

	public void setUserHavaInterestMoney(Double userHavaInterestMoney) {
		this.userHavaInterestMoney = userHavaInterestMoney;
	}

}
