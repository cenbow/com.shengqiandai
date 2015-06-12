package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

/**
 * 借款客户信息
 * @author pc3
 *
 */
public class BorrowVO1 extends BaseVO{
	//id
	private int id;

	//发标日期
	private String verifyTime;
	//借款金额
	private String account;
	//客户姓名
	private String ownerName;
	//抵押品种
	private String mortgageType;
	//借款标题
	private String name;
	//借款期限
	private String timeLimit;
	//借款期限
	private String contractStart;
	//合同开始期限
	private String contractEnd;
	//合同结束期限
	private String successTime;
	//满标日期
	private String endTime;
	
	
	public String getTimeLimit() {
		return timeLimit;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}
	public String getVerifyTime() {
		return verifyTime;
	}
	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getOwnerName() {
		return ownerName;
	}
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	public String getMortgageType() {
		return mortgageType;
	}
	public void setMortgageType(String mortgageType) {
		this.mortgageType = mortgageType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContractStart() {
		return contractStart;
	}
	public void setContractStart(String contractStart) {
		this.contractStart = contractStart;
	}
	public String getContractEnd() {
		return contractEnd;
	}
	public void setContractEnd(String contractEnd) {
		this.contractEnd = contractEnd;
	}
	public String getSuccessTime() {
		return successTime;
	}
	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	

}
