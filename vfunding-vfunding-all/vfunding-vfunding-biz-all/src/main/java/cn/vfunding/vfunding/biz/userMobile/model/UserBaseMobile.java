package cn.vfunding.vfunding.biz.userMobile.model;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserBaseMobile extends BaseModel  {

	private Integer userId;

	@RestAttribute(len = 0, name = "用户名称", remark = "用户名称", notnull = false)
	private String userName;

	@RestAttribute(len = 0, name = "总金额", remark = "总金额", notnull = false)
	private BigDecimal total;

	@RestAttribute(len = 0, name = "可用金额", remark = "可用金额", notnull = false)
	private BigDecimal useMoney;

	@RestAttribute(len = 0, name = "冻结金额", remark = "冻结金额", notnull = false)
	private BigDecimal noUseMoney;

	@RestAttribute(len = 0, name = "待收金额", remark = "待收金额", notnull = false)
	private BigDecimal collection;

	@RestAttribute(len = 0, name = "红包", remark = "红包", notnull = false)
	private BigDecimal hongbao;

	@RestAttribute(len = 0, name = "体验金", remark = "体验金", notnull = false)
	private BigDecimal feelMoney;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public BigDecimal getNoUseMoney() {
		return noUseMoney;
	}

	public void setNoUseMoney(BigDecimal noUseMoney) {
		this.noUseMoney = noUseMoney;
	}

	public BigDecimal getHongbao() {
		return hongbao;
	}

	public void setHongbao(BigDecimal hongbao) {
		this.hongbao = hongbao;
	}

	public BigDecimal getFeelMoney() {
		return feelMoney;
	}

	public void setFeelMoney(BigDecimal feelMoney) {
		this.feelMoney = feelMoney;
	}

	public BigDecimal getCollection() {
		return collection;
	}

	public void setCollection(BigDecimal collection) {
		this.collection = collection;
	}

}