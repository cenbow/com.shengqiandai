package cn.vfunding.vfunding.biz.common.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class BorrowTenderVO extends BaseVO {

	// 投标Id
	private Integer id;
	// 投标人
	private String tenderUser;
	// 投标人id
	private Integer tenderUserId;
	// 借款人
	private String borrowUser;
	// 借款者积分
	private Integer value;
	// 标名称
	private String borrowName;
	// 年利率
	private BigDecimal apr;
	// 服务费（%）
	private BigDecimal bfee;
	// 担保费（%）
	private BigDecimal gfee;
	// 投标金额
	private String money;
	// 有效金额
	private String account;
	// 待还金额
	private String repaymentAccount;
	// 已还金额
	private String repaymentYesAccount;
	// 期限
	private Integer timeLimit;
	// 投标时间
	private Integer addtime;
	// 投标状态
	private Integer status;

	private Integer credit;// 积分

	private Integer allOrder;// 总期次

	private Integer noOrder;// 待收期次

	private String tenderTime;

	private String borrowStartTime;// 借款开始日

	private String borrowEndTime;// 借款结束日

	private String eachTime;// 每月还款日

	private BigDecimal bfeeMoney;// 服务费
	private BigDecimal gfeeMoney;// 担保费用

	private String statusStr ="未知状态";

	public String getStatusStr() {
		if (status == 5) {
			return "待审核";
		} else if (status == 2) {
			return "投资失败";
		} else if (status == 1 && new Double(repaymentAccount) - new Double(repaymentYesAccount) > 0) {
			return "还款中";
		} else if (status == 1 && new Double(repaymentAccount) - new Double(repaymentYesAccount) == 0) {
			return "已还款";
		}
		return statusStr;
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

	public BigDecimal getBfeeMoney() {
		return bfeeMoney;
	}

	public void setBfeeMoney(BigDecimal bfeeMoney) {
		this.bfeeMoney = bfeeMoney;
	}

	public BigDecimal getGfeeMoney() {
		return gfeeMoney;
	}

	public void setGfeeMoney(BigDecimal gfeeMoney) {
		this.gfeeMoney = gfeeMoney;
	}

	public String getBorrowStartTime() {
		return borrowStartTime;
	}

	public void setBorrowStartTime(String borrowStartTime) {
		this.borrowStartTime = borrowStartTime;
	}

	public String getBorrowEndTime() {
		return borrowEndTime;
	}

	public void setBorrowEndTime(String borrowEndTime) {
		this.borrowEndTime = borrowEndTime;
	}

	public String getEachTime() {
		return eachTime;
	}

	public void setEachTime(String eachTime) {
		this.eachTime = eachTime;
	}

	public Integer getAllOrder() {
		return allOrder;
	}

	public void setAllOrder(Integer allOrder) {
		this.allOrder = allOrder;
	}

	public Integer getNoOrder() {
		return noOrder;
	}

	public void setNoOrder(Integer noOrder) {
		this.noOrder = noOrder;
	}

	public Integer getCredit() {
		return credit;
	}

	public void setCredit(Integer credit) {
		this.credit = credit;
	}

	public String getRepaymentYesAccount() {
		return repaymentYesAccount;
	}

	public void setRepaymentYesAccount(String repaymentYesAccount) {
		this.repaymentYesAccount = repaymentYesAccount;
	}

	public Integer getTenderUserId() {
		return tenderUserId;
	}

	public void setTenderUserId(Integer tenderUserId) {
		this.tenderUserId = tenderUserId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTenderUser() {
		return tenderUser;
	}

	public void setTenderUser(String tenderUser) {
		this.tenderUser = tenderUser;
	}

	public String getBorrowUser() {
		return borrowUser;
	}

	public void setBorrowUser(String borrowUser) {
		this.borrowUser = borrowUser;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getBfee() {
		return bfee;
	}

	public void setBfee(BigDecimal bfee) {
		this.bfee = bfee;
	}

	public BigDecimal getGfee() {
		return gfee;
	}

	public void setGfee(BigDecimal gfee) {
		this.gfee = gfee;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getTenderTime() {
		return tenderTime;
	}

	public void setTenderTime(String tenderTime) {
		this.tenderTime = tenderTime;
	}

}
