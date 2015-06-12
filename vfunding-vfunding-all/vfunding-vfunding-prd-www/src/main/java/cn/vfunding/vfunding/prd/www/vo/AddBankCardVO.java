package cn.vfunding.vfunding.prd.www.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class AddBankCardVO extends BaseVO {

	private String cardNum;
	
	private String bankId;
	
	private String openCity;
	
	private String openAddress;

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

	public String getOpenCity() {
		return openCity;
	}

	public void setOpenCity(String openCity) {
		this.openCity = openCity;
	}

	public String getOpenAddress() {
		return openAddress;
	}

	public void setOpenAddress(String openAddress) {
		this.openAddress = openAddress;
	}
	
	
}
