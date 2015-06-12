package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class FinalVerifyVO extends BaseVO{

	private Integer borrowId;
	
	private String remark;
	
	private Integer status;
	
	private Integer adminUserId;
	
	private String ip;

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
