package cn.vfunding.vfunding.biz.account.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class CashNumber extends BaseModel{
	private Integer id;

	private Integer cashId;

	private Integer addUserid;

	private String bankcashNo;

	private Date addTime;

	private Date updateTime;
	
	

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCashId() {
		return cashId;
	}

	public void setCashId(Integer cashId) {
		this.cashId = cashId;
	}

	public Integer getAddUserid() {
		return addUserid;
	}

	public void setAddUserid(Integer addUserid) {
		this.addUserid = addUserid;
	}

	public String getBankcashNo() {
		return bankcashNo;
	}

	public void setBankcashNo(String bankcashNo) {
		this.bankcashNo = bankcashNo == null ? null : bankcashNo.trim();
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}
}