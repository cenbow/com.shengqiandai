package cn.p2p.common.plat.file.model;

import java.io.Serializable;

public class TestModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3211361387264663316L;
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
