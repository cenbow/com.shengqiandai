package cn.vfunding.vfunding.biz.week.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.account.model.Account;
import cn.vfunding.vfunding.biz.user.model.UserWithBLOBs;
import cn.vfunding.vfunding.biz.week.model.WeekUser;

@SuppressWarnings("serial")
public class WeekUserVO extends BaseVO{
	private UserWithBLOBs user;
	private WeekUser weekUser;
	private Account account;
	public UserWithBLOBs getUser() {
		return user;
	}
	public void setUser(UserWithBLOBs user) {
		this.user = user;
	}
	public WeekUser getWeekUser() {
		return weekUser;
	}
	public void setWeekUser(WeekUser weekUser) {
		this.weekUser = weekUser;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
}
