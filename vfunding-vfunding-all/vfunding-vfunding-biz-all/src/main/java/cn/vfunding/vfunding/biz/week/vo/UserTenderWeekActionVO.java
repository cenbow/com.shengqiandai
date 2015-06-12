package cn.vfunding.vfunding.biz.week.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;


/**
 * 用户投资参数VO
 * 
 * @author lilei
 * 
 */
@SuppressWarnings("serial")
public class UserTenderWeekActionVO extends BaseVO{

	private String paypassword;

	private Integer userId;

	private Integer weekId;

	private Integer buyShare;

	private Integer puserId;

	private Integer userType;

	private String userip;

	private Integer isUseVolume;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	
	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public String getPaypassword() {
		return paypassword;
	}

	public void setPaypassword(String paypassword) {
		this.paypassword = paypassword;
	}


	
	public Integer getBuyShare() {
		return buyShare;
	}

	public void setBuyShare(Integer buyShare) {
		this.buyShare = buyShare;
	}

	public Integer getPuserId() {
		return puserId;
	}

	public void setPuserId(Integer puserId) {
		this.puserId = puserId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getUserip() {
		return userip;
	}

	public void setUserip(String userip) {
		this.userip = userip;
	}

	public Integer getIsUseVolume() {
		return isUseVolume;
	}

	public void setIsUseVolume(Integer isUseVolume) {
		this.isUseVolume = isUseVolume;
	}

}
