package cn.vfunding.vfunding.biz.system.model;

import java.io.Serializable;

public class UserTrack implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserTrack() {
		super();
	}

	public UserTrack(String userId, String loginType) {
		super();
		this.userId = userId;
		this.loginType = loginType;
	}

	
	private Integer id;

	private String userId;

	private String loginTime;
	
	private String lastReqTime;

	private String outTime;

	private String loginIp;

	private String loginType;
	
	private String sessionId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime == null ? null : loginTime.trim();
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime == null ? null : outTime.trim();
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp == null ? null : loginIp.trim();
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType == null ? null : loginType.trim();
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public String getLastReqTime() {
		return lastReqTime;
	}

	public void setLastReqTime(String lastReqTime) {
		this.lastReqTime = lastReqTime;
	}
	
	
}