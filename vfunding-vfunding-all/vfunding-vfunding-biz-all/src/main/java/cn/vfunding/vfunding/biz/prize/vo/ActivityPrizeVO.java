package cn.vfunding.vfunding.biz.prize.vo;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class ActivityPrizeVO extends BaseModel{

	private String username;
	
	private Integer inviteUserid;
	
	private Integer allcount;
	
	private BigDecimal allaccount;

	public Integer getInviteUserid() {
		return inviteUserid;
	}

	public void setInviteUserid(Integer inviteUserid) {
		this.inviteUserid = inviteUserid;
	}

	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	public BigDecimal getAllaccount() {
		return allaccount;
	}

	public void setAllaccount(BigDecimal allaccount) {
		this.allaccount = allaccount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	

}
