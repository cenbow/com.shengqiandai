package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 用户投资记录
 * @author pc3
 *
 */
public class InvesVO extends BaseVO{
	private String id;
	private String addtime;
	private String borrowName;
	private String apr;
	private String bfee;
	private String gfee;
	private String timeLimit;
	private String status;
	private String repaymentAccount;
	private String account;
	private String repaymentYesAccount;
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getApr() {
		return apr;
	}

	public void setApr(String apr) {
		this.apr = apr;
	}

	public String getBfee() {
		return bfee;
	}

	public void setBfee(String bfee) {
		this.bfee = bfee;
	}

	public String getGfee() {
		return gfee;
	}

	public void setGfee(String gfee) {
		this.gfee = gfee;
	}

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(String repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getRepaymentYesAccount() {
		return repaymentYesAccount;
	}

	public void setRepaymentYesAccount(String repaymentYesAccount) {
		this.repaymentYesAccount = repaymentYesAccount;
	}
}
