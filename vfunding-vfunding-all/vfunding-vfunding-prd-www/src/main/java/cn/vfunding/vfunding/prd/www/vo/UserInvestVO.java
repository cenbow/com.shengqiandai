package cn.vfunding.vfunding.prd.www.vo;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.common.framework.utils.rest.annotation.RestAttribute;

/**
 * 用户投资VO
 * @author ylwl1
 *
 */
public class UserInvestVO extends BaseVO {

	/**
	 * 投资者ID
	 */
	@RestAttribute(len = 0, name = "投资者ID", remark = "投资者ID", notnull = true)
	private Integer userId;
	
	/**
	 * 投资时间
	 */
	@RestAttribute(len = 0, name = "投资时间", remark = "投资时间", notnull = true)
	private Date investTime;
	/**
	 * 投资金额
	 */
	@RestAttribute(len = 0, name = "投资金额", remark = "投资者金额", notnull = true)
	private Float money;
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Date getInvestTime() {
		return investTime;
	}
	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}
	public Float getMoney() {
		return money;
	}
	public void setMoney(Float money) {
		this.money = money;
	}
	
	
}
