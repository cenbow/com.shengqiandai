package cn.vfunding.vfunding.biz.prize.vo;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class PrizeRepositoryVO extends BaseVO{
	private Date chooseTime;
	
	private String prizeName;
	
	private String userName;

	public Date getChooseTime() {
		return chooseTime;
	}

	public void setChooseTime(Date chooseTime) {
		this.chooseTime = chooseTime;
	}

	public String getPrizeName() {
		return prizeName;
	}

	public void setPrizeName(String prizeName) {
		this.prizeName = prizeName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
