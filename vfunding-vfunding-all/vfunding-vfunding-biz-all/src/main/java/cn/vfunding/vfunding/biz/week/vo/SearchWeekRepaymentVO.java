package cn.vfunding.vfunding.biz.week.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class SearchWeekRepaymentVO extends BaseVO{
	private Integer weekId;
	
	private String weekName;
	
	private Integer status;
	
	private String startTime;
	
	private String endTime;

	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public String getWeekName() {
		return weekName;
	}

	public void setWeekName(String weekName) {
		this.weekName = weekName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
