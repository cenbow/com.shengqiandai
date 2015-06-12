package cn.p2p.p2p.biz.current.vo;

import java.math.BigDecimal;

import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;
import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserAccountActionResultVO extends BaseModel{
	@RestAttribute(len = 0, name = "投资状态", remark = "1:全额投资,2:部分投资,3:已满标", notnull = false)
	private Integer status;
	/**
	 * 用户投资金额
	 */
	private BigDecimal payMoney;
	/**
	 * 实际投资金额
	 */
	private BigDecimal realPayMoney;
	
	
	private Integer tenderId;
	
	/**
	 * 用户ID
	 */
	private Integer userId;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(BigDecimal payMoney) {
		this.payMoney = payMoney;
	}

	public BigDecimal getRealPayMoney() {
		return realPayMoney;
	}

	public void setRealPayMoney(BigDecimal realPayMoney) {
		this.realPayMoney = realPayMoney;
	}



	/**
	 * @return the tenderId
	 */
	public Integer getTenderId() {
		return tenderId;
	}

	/**
	 * @param tenderId the tenderId to set
	 */
	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
}
