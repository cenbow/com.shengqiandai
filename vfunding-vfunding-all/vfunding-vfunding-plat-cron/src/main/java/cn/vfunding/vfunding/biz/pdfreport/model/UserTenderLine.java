package cn.vfunding.vfunding.biz.pdfreport.model;

public class UserTenderLine {

	private Integer userId;

	private String yearMonth;

	private Double tenderMoney;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

	public Double getTenderMoney() {
		if (tenderMoney == null) {
			tenderMoney = 0d;
		}
		return tenderMoney;
	}

	public void setTenderMoney(Double tenderMoney) {
		this.tenderMoney = tenderMoney;
	}

}
