package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.account.model.AccountFeel;

/**
 *个人中心 VO
 */
public class AccountVO extends BaseVO{
	
	private Account account;//账户金额
	
	//体验标
	private AccountFeel accountFeel;//体验金账户
	private String repayTime;//体验标还款时间
	
	private BigDecimal sumIncome;//累计总收益
	
	private BigDecimal backIncome;//返利总收益
	
	private BigDecimal weightIncome;//加权收益
	
	private BigDecimal sumBorrowAccount;//借款总金额
	
	private BigDecimal recentWaitCollection;//近期待收
	private String collectionTime;//近期待收时间
	private String feelCollectionTime;//体验金待收时间
	
	//还款数据
	private BigDecimal sumRepay;//待还总金额
	private Integer countRepay;//待还笔数
	private BigDecimal recentRepay;//近期待还
	private Integer recentRepayTime;//近期还款时间
	//投资账户
	private BigDecimal sumCollection;//待收总额
	private Integer countCollection;//待收笔数
	private BigDecimal recentCollection;//最近待收总额
	private Integer recentCollectionTime;//最近待收时间

	private BigDecimal averageApr;//加权收益
	
	
	
	public BigDecimal getAverageApr() {
		return averageApr;
	}

	public void setAverageApr(BigDecimal averageApr) {
		this.averageApr = averageApr;
	}

	public BigDecimal getSumCollection() {
		return sumCollection;
	}

	public void setSumCollection(BigDecimal sumCollection) {
		this.sumCollection = sumCollection;
	}


	public Integer getCountCollection() {
		return countCollection;
	}

	public void setCountCollection(Integer countCollection) {
		this.countCollection = countCollection;
	}

	public BigDecimal getRecentCollection() {
		return recentCollection;
	}

	public void setRecentCollection(BigDecimal recentCollection) {
		this.recentCollection = recentCollection;
	}

	public Integer getRecentCollectionTime() {
		return recentCollectionTime;
	}

	public void setRecentCollectionTime(Integer recentCollectionTime) {
		this.recentCollectionTime = recentCollectionTime;
	}

	public BigDecimal getSumRepay() {
		return sumRepay;
	}

	public void setSumRepay(BigDecimal sumRepay) {
		this.sumRepay = sumRepay;
	}

	public Integer getCountRepay() {
		return countRepay;
	}

	public void setCountRepay(Integer countRepay) {
		this.countRepay = countRepay;
	}

	public BigDecimal getRecentRepay() {
		return recentRepay;
	}

	public void setRecentRepay(BigDecimal recentRepay) {
		this.recentRepay = recentRepay;
	}

	public Integer getRecentRepayTime() {
		return recentRepayTime;
	}

	public void setRecentRepayTime(Integer recentRepayTime) {
		this.recentRepayTime = recentRepayTime;
	}

	public String getFeelCollectionTime() {
		return feelCollectionTime;
	}

	public void setFeelCollectionTime(String feelCollectionTime) {
		this.feelCollectionTime = feelCollectionTime;
	}

	public String getCollectionTime() {
		return collectionTime;
	}

	public void setCollectionTime(String collectionTime) {
		this.collectionTime = collectionTime;
	}

	public String getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(String repayTime) {
		this.repayTime = repayTime;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public AccountFeel getAccountFeel() {
		return accountFeel;
	}

	public void setAccountFeel(AccountFeel accountFeel) {
		this.accountFeel = accountFeel;
	}

	public BigDecimal getSumIncome() {
		return sumIncome;
	}

	public void setSumIncome(BigDecimal sumIncome) {
		this.sumIncome = sumIncome;
	}

	public BigDecimal getBackIncome() {
		return backIncome;
	}

	public void setBackIncome(BigDecimal backIncome) {
		this.backIncome = backIncome;
	}

	public BigDecimal getWeightIncome() {
		return weightIncome;
	}

	public void setWeightIncome(BigDecimal weightIncome) {
		this.weightIncome = weightIncome;
	}

	public BigDecimal getSumBorrowAccount() {
		return sumBorrowAccount;
	}

	public void setSumBorrowAccount(BigDecimal sumBorrowAccount) {
		this.sumBorrowAccount = sumBorrowAccount;
	}

	public BigDecimal getRecentWaitCollection() {
		return recentWaitCollection;
	}

	public void setRecentWaitCollection(BigDecimal recentWaitCollection) {
		this.recentWaitCollection = recentWaitCollection;
	}
}
