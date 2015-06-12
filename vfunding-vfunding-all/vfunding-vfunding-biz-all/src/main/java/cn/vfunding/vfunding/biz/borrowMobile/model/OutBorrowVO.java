package cn.vfunding.vfunding.biz.borrowMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

@SuppressWarnings("serial")
public class OutBorrowVO extends BorrowBaseMobile {

	

	@RestAttribute(len = 0, name = "标的类型", remark = "标的类型", notnull = false)
	private String biaoType;

	@RestAttribute(len = 0, name = "需还金额", remark = "需还金额", notnull = false)
	private BigDecimal repaymentAccount;

	@RestAttribute(len = 0, name = "已还金额", remark = "已还金额", notnull = false)
	private BigDecimal repaymentYesAccount;

	@RestAttribute(len = 0, name = "标的状态", remark = "91:投,92:满,93:还,94:结,(体验金)910:投,920:满,930:还,940:结", notnull = false)
	private Integer status;

	@RestAttribute(len = 0, name = "发表名称", remark = "用户名称", notnull = false)
	private String userName;

	@RestAttribute(len = 0, name = "最少投资金额", remark = "最少投资金额", notnull = false)
	private BigDecimal lowestAccount;

	@RestAttribute(len = 0, name = "最大投资金额", remark = "最大投资金额", notnull = false)
	private BigDecimal mostAccount;

	@RestAttribute(len = 0, name = "投资结束时间", remark = "投资结束时间", notnull = false)
	private String tenderEndtime;

	@RestAttribute(len = 0, name = "类型", remark = "类型", notnull = false)
	private Integer style;

	@RestAttribute(len = 0, name = "投标次数", remark = "投标次数", notnull = false)
	private Integer tenderborrowCount;

	@RestAttribute(len = 0, name = "满标时间", remark = "满标时间", notnull = false)
	private String successTime;

	@RestAttribute(len = 0, name = "复审时间", remark = "复审时间", notnull = false)
	private String verifyTime;

	public BigDecimal getLowestAccount() {
		return lowestAccount;
	}

	public void setLowestAccount(BigDecimal lowestAccount) {
		this.lowestAccount = lowestAccount;
	}

	public BigDecimal getMostAccount() {
		return mostAccount;
	}

	public void setMostAccount(BigDecimal mostAccount) {
		this.mostAccount = mostAccount;
	}

	public String getTenderEndtime() {
		return tenderEndtime;
	}

	public void setTenderEndtime(String tenderEndtime) {
		this.tenderEndtime = tenderEndtime;
	}

	public String getSuccessTime() {
		return successTime;
	}

	public void setSuccessTime(String successTime) {
		this.successTime = successTime;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public Integer getTenderborrowCount() {
		return tenderborrowCount;
	}

	public void setTenderborrowCount(Integer tenderborrowCount) {
		this.tenderborrowCount = tenderborrowCount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(String verifyTime) {
		this.verifyTime = verifyTime;
	}

	public String getBiaoType() {
		return biaoType;
	}

	public void setBiaoType(String biaoType) {
		this.biaoType = biaoType;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}