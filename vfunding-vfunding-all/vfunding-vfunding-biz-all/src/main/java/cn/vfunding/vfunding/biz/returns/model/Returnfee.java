package cn.vfunding.vfunding.biz.returns.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Returnfee extends BaseModel  {
	private Integer id;

	private Integer userType;

	private BigDecimal returnFee;

	private BigDecimal commissionFee;

	private String addtime;

	private String addip;

	private Integer rtype;

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public BigDecimal getReturnFee() {
		return returnFee;
	}

	public void setReturnFee(BigDecimal returnFee) {
		this.returnFee = returnFee;
	}

	public BigDecimal getCommissionFee() {
		return commissionFee;
	}

	public void setCommissionFee(BigDecimal commissionFee) {
		this.commissionFee = commissionFee;
	}

	public String getAddtime() {
		return addtime;
	}

	public void setAddtime(String addtime) {
		this.addtime = addtime == null ? null : addtime.trim();
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip == null ? null : addip.trim();
	}
}