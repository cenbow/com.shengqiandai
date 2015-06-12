package cn.vfunding.vfunding.biz.sinaBalance.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class SinaBalanceLog implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private Integer userId;

    private BigDecimal balance;

    private BigDecimal availableBalance;

    private BigDecimal bonusYesterday;

    private BigDecimal bonusMonth;

    private BigDecimal bonusTotal;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public BigDecimal getBonusYesterday() {
		return bonusYesterday;
	}

	public void setBonusYesterday(BigDecimal bonusYesterday) {
		this.bonusYesterday = bonusYesterday;
	}

	public BigDecimal getBonusMonth() {
		return bonusMonth;
	}

	public void setBonusMonth(BigDecimal bonusMonth) {
		this.bonusMonth = bonusMonth;
	}

	public BigDecimal getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(BigDecimal bonusTotal) {
		this.bonusTotal = bonusTotal;
	}

  
}