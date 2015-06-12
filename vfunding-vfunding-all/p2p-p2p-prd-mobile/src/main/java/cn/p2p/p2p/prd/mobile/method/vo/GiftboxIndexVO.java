package cn.p2p.p2p.prd.mobile.method.vo;

public class GiftboxIndexVO {
	private String sumUseMoney;
	private String hikesUseRate;
	private String cashHongbao;
	private String otherGift;
	private boolean giftFlag;
	private boolean hikesFlag;
	private boolean	cashFlag;
	private boolean otherFlag;
	
	
	public boolean isGiftFlag() {
		return giftFlag;
	}
	public void setGiftFlag(boolean giftFlag) {
		this.giftFlag = giftFlag;
	}
	public boolean isHikesFlag() {
		return hikesFlag;
	}
	public void setHikesFlag(boolean hikesFlag) {
		this.hikesFlag = hikesFlag;
	}
	public boolean isCashFlag() {
		return cashFlag;
	}
	public void setCashFlag(boolean cashFlag) {
		this.cashFlag = cashFlag;
	}
	public boolean isOtherFlag() {
		return otherFlag;
	}
	public void setOtherFlag(boolean otherFlag) {
		this.otherFlag = otherFlag;
	}
	public String getOtherGift() {
		return otherGift;
	}
	public void setOtherGift(String otherGift) {
		this.otherGift = otherGift;
	}
	public String getSumUseMoney() {
		return sumUseMoney;
	}
	public void setSumUseMoney(String sumUseMoney) {
		this.sumUseMoney = sumUseMoney;
	}
	public String getHikesUseRate() {
		return hikesUseRate;
	}
	public void setHikesUseRate(String hikesUseRate) {
		this.hikesUseRate = hikesUseRate;
	}
	public String getCashHongbao() {
		return cashHongbao;
	}
	public void setCashHongbao(String cashHongbao) {
		this.cashHongbao = cashHongbao;
	}
	
}
