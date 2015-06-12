package cn.vfunding.common.plat.sender.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class RealNameVO extends BaseVO {

	public RealNameVO() {

	}

	public RealNameVO(String name, String cardId) {
		this.name = name;
		this.cardId = cardId;
	}

	private String name;

	private String cardId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

}
