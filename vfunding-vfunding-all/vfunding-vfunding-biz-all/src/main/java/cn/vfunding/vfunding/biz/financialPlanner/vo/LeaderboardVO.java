package cn.vfunding.vfunding.biz.financialPlanner.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class LeaderboardVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7096084496656821790L;

	private Integer userId;
	
	private String username;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
