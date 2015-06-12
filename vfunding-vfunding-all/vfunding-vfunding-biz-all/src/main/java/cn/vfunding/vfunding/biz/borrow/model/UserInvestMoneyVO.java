package cn.vfunding.vfunding.biz.borrow.model;

import cn.vfunding.vfunding.common.model.BaseModel;

/**
 * 用户在指定时间内投资金额查询条件VO对象
 * 
 * @author liuyijun
 * 
 */
@SuppressWarnings("serial")
public class UserInvestMoneyVO extends  BaseModel{

	
	public UserInvestMoneyVO(){
		super();
	}
	
	public UserInvestMoneyVO(Integer days,Integer userId){
		super();
		this.days=days;
		this.userId=userId;
	}
	private Integer days;
	private Integer userId;

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
