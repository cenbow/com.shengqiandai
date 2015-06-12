package cn.vfunding.vfunding.biz.system.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

/**
 * 统计网站部分资金信息
 * @author liuhuan
 */
@SuppressWarnings("serial")
public class AllStatistics extends  BaseModel{
	//投资总额
	private BigDecimal allTenderMoney;
	//提现总额
	private BigDecimal allCashMoney;
	//充值总额
	private BigDecimal allRechargeMoney;
	//所得利息总额
	private BigDecimal allInterestMoney;
	
	
	//借款成功次数
	private Integer countBorrowSucc;
	//流标次数
	private Integer countBorrowFail;
	//待还笔数
	private Integer countWaitRepay;
	//成功还款笔数
	private Integer countSuccRepay;
	//提前还款
	private Integer countForwardRepay;
	//迟到还款
	private Integer countLateRepay;
	//30天之内的逾期还款笔数
	private Integer countLate30Days;
	//超过30天的逾期还款笔数
	private Integer countLate30Days_;
	//逾期未还款笔数
	private Integer countLateNoRepay;
	
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

	public BigDecimal getAllTenderMoney() {
		return allTenderMoney;
	}

	public void setAllTenderMoney(BigDecimal allTenderMoney) {
		this.allTenderMoney = allTenderMoney;
	}

	public BigDecimal getAllCashMoney() {
		return allCashMoney;
	}

	public void setAllCashMoney(BigDecimal allCashMoney) {
		this.allCashMoney = allCashMoney;
	}

	public BigDecimal getAllRechargeMoney() {
		return allRechargeMoney;
	}

	public void setAllRechargeMoney(BigDecimal allRechargeMoney) {
		this.allRechargeMoney = allRechargeMoney;
	}

	public BigDecimal getAllInterestMoney() {
		return allInterestMoney;
	}

	public void setAllInterestMoney(BigDecimal allInterestMoney) {
		this.allInterestMoney = allInterestMoney;
	}

	public Integer getCountBorrowSucc() {
		return countBorrowSucc;
	}

	public void setCountBorrowSucc(Integer countBorrowSucc) {
		this.countBorrowSucc = countBorrowSucc;
	}

	public Integer getCountBorrowFail() {
		return countBorrowFail;
	}

	public void setCountBorrowFail(Integer countBorrowFail) {
		this.countBorrowFail = countBorrowFail;
	}

	public Integer getCountWaitRepay() {
		return countWaitRepay;
	}

	public void setCountWaitRepay(Integer countWaitRepay) {
		this.countWaitRepay = countWaitRepay;
	}

	public Integer getCountSuccRepay() {
		return countSuccRepay;
	}

	public void setCountSuccRepay(Integer countSuccRepay) {
		this.countSuccRepay = countSuccRepay;
	}

	public Integer getCountForwardRepay() {
		return countForwardRepay;
	}

	public void setCountForwardRepay(Integer countForwardRepay) {
		this.countForwardRepay = countForwardRepay;
	}

	public Integer getCountLateRepay() {
		return countLateRepay;
	}

	public void setCountLateRepay(Integer countLateRepay) {
		this.countLateRepay = countLateRepay;
	}

	public Integer getCountLate30Days() {
		return countLate30Days;
	}

	public void setCountLate30Days(Integer countLate30Days) {
		this.countLate30Days = countLate30Days;
	}

	public Integer getCountLate30Days_() {
		return countLate30Days_;
	}

	public void setCountLate30Days_(Integer countLate30Days_) {
		this.countLate30Days_ = countLate30Days_;
	}

	public Integer getCountLateNoRepay() {
		return countLateNoRepay;
	}

	public void setCountLateNoRepay(Integer countLateNoRepay) {
		this.countLateNoRepay = countLateNoRepay;
	}
}
