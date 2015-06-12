package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.vfunding.biz.borrow.model.Borrow;

//初审时,列表VO
public class TheTrialBorrowVO extends Borrow {

	private String username;

	private String ownername;


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
}
