package cn.p2p.p2p.biz.current.model;

import java.math.BigDecimal;
import java.util.Date;

public class Current {
	private Integer currentId;

	private String currentName;

	private BigDecimal sumMoney;

	private BigDecimal buyMoney;

	private BigDecimal apr;

	private Integer tenderCount;

	private Integer appointmentCount;

	private Integer status;

	private Date verifyTime;

	private Date saleTime;

	private Date soldoutTime;

	private Date addTime;

	private String statusStr;

	private CurrentRule currentRule;

	public CurrentRule getCurrentRule() {
		return currentRule;
	}

	public void setCurrentRule(CurrentRule currentRule) {
		this.currentRule = currentRule;
	}

	public Integer getCurrentId() {
		return currentId;
	}

	public void setCurrentId(Integer currentId) {
		this.currentId = currentId;
	}

	public String getCurrentName() {
		return currentName;
	}

	public void setCurrentName(String currentName) {
		this.currentName = currentName == null ? null : currentName.trim();
	}

	public BigDecimal getSumMoney() {
		return sumMoney;
	}

	public void setSumMoney(BigDecimal sumMoney) {
		this.sumMoney = sumMoney;
	}

	public BigDecimal getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(BigDecimal buyMoney) {
		this.buyMoney = buyMoney;
	}

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public Integer getTenderCount() {
		return tenderCount;
	}

	public void setTenderCount(Integer tenderCount) {
		this.tenderCount = tenderCount;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getVerifyTime() {
		return verifyTime;
	}

	public void setVerifyTime(Date verifyTime) {
		this.verifyTime = verifyTime;
	}

	public Date getSaleTime() {
		return saleTime;
	}

	public void setSaleTime(Date saleTime) {
		this.saleTime = saleTime;
	}

	public Date getSoldoutTime() {
		return soldoutTime;
	}

	public void setSoldoutTime(Date soldoutTime) {
		this.soldoutTime = soldoutTime;
	}

	public Date getAddTime() {
		return addTime;
	}

	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public Integer getAppointmentCount() {
		return appointmentCount;
	}

	public void setAppointmentCount(Integer appointmentCount) {
		this.appointmentCount = appointmentCount;
	}

	public String getStatusStr() {
		if (status != null && status == 1) {
			if (saleTime.after(new Date())) {
				return "预告";
			} else if (buyMoney.doubleValue() < sumMoney.doubleValue()) {
				return "热销";
			} else {
				return "售罄";
			}
		} else if (status != null && status == 3) {
			return "售罄";
		}
		return "";
	}

	public void setStatusStr(String statusStr) {
		this.statusStr = statusStr;
	}

}