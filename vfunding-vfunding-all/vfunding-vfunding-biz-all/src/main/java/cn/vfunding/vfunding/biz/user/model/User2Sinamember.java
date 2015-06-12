package cn.vfunding.vfunding.biz.user.model;

import java.math.BigDecimal;


public class User2Sinamember extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5661054783938242207L;
	
	
	private BigDecimal use_money;

	public BigDecimal getUse_money() {
		return use_money;
	}

	public void setUse_money(BigDecimal use_money) {
		this.use_money = use_money;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User2Sinamember [use_money=");
		builder.append(use_money);
		builder.append(", toString()=");
		builder.append(super.toString());
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	
}