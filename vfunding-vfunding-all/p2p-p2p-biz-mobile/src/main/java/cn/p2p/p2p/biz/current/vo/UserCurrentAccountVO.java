package cn.p2p.p2p.biz.current.vo;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserCurrentAccountVO extends BaseModel  {

	private Integer userId;

	private BigDecimal money;

	private Integer currentId;

	private String userip;
	
	private Integer toCard;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public Integer getToCard() {
		return toCard;
	}

	public void setToCard(Integer toCard) {
		this.toCard = toCard;
	}
	
}
