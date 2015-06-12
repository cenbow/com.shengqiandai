package cn.vfunding.vfunding.biz.userMobile.model;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserTenderMobile extends BaseModel  {

	@RestAttribute(len = 0, name = "标名称", remark = "标名称", notnull = false)
	private String borrowName;

	@RestAttribute(len = 0, name = "投资金额", remark = "投资金额", notnull = false)
	private Double account;

	@RestAttribute(len = 0, name = "投资状态", remark = "状态:1:投资成功,5:投资待审,2:投资失败", notnull = false)
	private Integer status;

	@RestAttribute(len = 0, name = "待收总额", remark = "代收总额", notnull = false)
	private String tenderTime;

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public Double getAccount() {
		return account;
	}

	public void setAccount(Double account) {
		this.account = account;
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
