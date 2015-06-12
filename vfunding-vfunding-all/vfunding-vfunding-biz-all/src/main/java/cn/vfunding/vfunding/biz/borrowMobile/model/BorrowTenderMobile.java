package cn.vfunding.vfunding.biz.borrowMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowTenderMobile extends BaseModel {

	

	@RestAttribute(len = 0, name = "用户姓名", remark = "用户姓名", notnull = false)
	private String userName;

	@RestAttribute(len = 0, name = "所投金额", remark = "所投金额", notnull = false)
	private BigDecimal tenderAccount;

	@RestAttribute(len = 0, name = "投资状态", remark = "1:成功,5:待审,其他不成功", notnull = false)
	private Integer tenderType;

	@RestAttribute(len = 0, name = "投资时间", remark = "投资时间", notnull = false)
	private String tenderTime;

	public String getUserName() {
		if (userName.length() > 3) {
			userName = userName.substring(0, 3) + "***";
		} else {
			userName = userName.substring(0, 1) + "***";
		}
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public BigDecimal getTenderAccount() {
		return tenderAccount;
	}

	public void setTenderAccount(BigDecimal tenderAccount) {
		this.tenderAccount = tenderAccount;
	}

	public void setTenderType(Integer tenderType) {
		this.tenderType = tenderType;
	}

	public Integer getTenderType() {
		return tenderType;
	}

	public String getTenderTime() {
		return tenderTime;
	}

	public void setTenderTime(String tenderTime) {
		this.tenderTime = tenderTime;
	}

}