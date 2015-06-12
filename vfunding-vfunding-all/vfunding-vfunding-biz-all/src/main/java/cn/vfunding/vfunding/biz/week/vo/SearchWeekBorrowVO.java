package cn.vfunding.vfunding.biz.week.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

@SuppressWarnings("serial")
public class SearchWeekBorrowVO extends BaseVO {
	private Integer weekId;
	
	private Integer id;
	
	private String status;

	public Integer getWeekId() {
		return weekId;
	}

	public void setWeekId(Integer weekId) {
		this.weekId = weekId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
