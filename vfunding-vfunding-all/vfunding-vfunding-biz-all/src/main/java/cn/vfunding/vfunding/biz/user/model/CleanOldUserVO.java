package cn.vfunding.vfunding.biz.user.model;

import java.io.Serializable;
import java.util.Date;

public class CleanOldUserVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5599958061212423987L;
	
	public CleanOldUserVO(){
		super();
	}
	
	public CleanOldUserVO(String unionUserId,Date lastReviveTime){
		super();
		this.unionUserId=unionUserId;
		this.lastReviveTime=lastReviveTime;
	}
	private String unionUserId;
	private Date lastReviveTime;
	public String getUnionUserId() {
		return unionUserId;
	}
	public void setUnionUserId(String unionUserId) {
		this.unionUserId = unionUserId;
	}
	public Date getLastReviveTime() {
		return lastReviveTime;
	}
	public void setLastReviveTime(Date lastReviveTime) {
		this.lastReviveTime = lastReviveTime;
	}
	
}
