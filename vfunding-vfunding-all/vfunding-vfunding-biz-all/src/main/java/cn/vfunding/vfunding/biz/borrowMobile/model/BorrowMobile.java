package cn.vfunding.vfunding.biz.borrowMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

@SuppressWarnings("serial")
public class BorrowMobile extends BorrowBaseMobile {

	

	@RestAttribute(len = 0, name = "标的类型", remark = "标的类型", notnull = false)
	private String biaoType;

	@RestAttribute(len = 0, name = "需还金额", remark = "需还金额", notnull = false)
	private BigDecimal repaymentAccount;

	@RestAttribute(len = 0, name = "已还金额", remark = "已还金额", notnull = false)
	private BigDecimal repaymentYesAccount;

	@RestAttribute(len = 0, name = "标的状态", remark = "91:投,92:满,93:还,94:结,(体验金)910:投,920:满,930:还,940:结", notnull = false)
	private Integer status;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

	public BigDecimal getRepaymentAccount() {
		return repaymentAccount;
	}

	public void setRepaymentAccount(BigDecimal repaymentAccount) {
		this.repaymentAccount = repaymentAccount;
	}

	public BigDecimal getRepaymentYesAccount() {
		return repaymentYesAccount;
	}

	public void setRepaymentYesAccount(BigDecimal repaymentYesAccount) {
		this.repaymentYesAccount = repaymentYesAccount;
	}

	public String getBiaoType() {
		return biaoType;
	}

	public void setBiaoType(String biaoType) {
		this.biaoType = biaoType;
	}

}