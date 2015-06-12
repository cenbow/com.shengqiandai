package cn.vfunding.vfunding.biz.system.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class InvestorsFee extends  BaseModel{

	private Integer id;
	private Integer bid;
	private BigDecimal bfee;// 平台服务费率
	private BigDecimal gfee; // 担保服务费率
	private BigDecimal bfeeType;
	private Integer addtime;
	private String addip;

	public BigDecimal getBfee() {
		return bfee;
	}

	public void setBfee(BigDecimal bfee) {
		this.bfee = bfee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public BigDecimal getGfee() {
		return gfee;
	}

	public void setGfee(BigDecimal gfee) {
		this.gfee = gfee;
	}

	public BigDecimal getBfeeType() {
		return bfeeType;
	}

	public void setBfeeType(BigDecimal bfeeType) {
		this.bfeeType = bfeeType;
	}

	public Integer getAddtime() {
		return addtime;
	}

	public void setAddtime(Integer addtime) {
		this.addtime = addtime;
	}

	public String getAddip() {
		return addip;
	}

	public void setAddip(String addip) {
		this.addip = addip;
	}
}
