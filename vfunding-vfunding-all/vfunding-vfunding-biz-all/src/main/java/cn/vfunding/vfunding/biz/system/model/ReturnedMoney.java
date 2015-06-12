package cn.vfunding.vfunding.biz.system.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class ReturnedMoney extends BaseModel  {
	private Integer id;
	private Integer userId;
	private BigDecimal total;
	private BigDecimal useMoney;
	private BigDecimal alreadyUseMoney;
	private BigDecimal notUseMoney;
	private Integer addtime;
	private String addip;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public BigDecimal getUseMoney() {
		return useMoney;
	}
	public void setUseMoney(BigDecimal useMoney) {
		this.useMoney = useMoney;
	}
	public BigDecimal getAlreadyUseMoney() {
		return alreadyUseMoney;
	}
	public void setAlreadyUseMoney(BigDecimal alreadyUseMoney) {
		this.alreadyUseMoney = alreadyUseMoney;
	}
	public BigDecimal getNotUseMoney() {
		return notUseMoney;
	}
	public void setNotUseMoney(BigDecimal notUseMoney) {
		this.notUseMoney = notUseMoney;
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
