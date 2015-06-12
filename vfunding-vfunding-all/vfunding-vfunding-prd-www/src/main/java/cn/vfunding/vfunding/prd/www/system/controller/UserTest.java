package cn.vfunding.vfunding.prd.www.system.controller;

import java.io.Serializable;

public class UserTest implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -90699091318698375L;
	private String userName;
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
