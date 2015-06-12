package cn.vfunding.vfunding.biz.common.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class QueryParameterVO extends BaseVO{

	private String name;

	private Integer type;

	private Integer status;

	private String endTime;

	private String startTime;

	private String biaoType;

	private Integer userId;

	private Integer timeLimit;

	private Integer style;
	
	


	public String getBiaoType() {
		return biaoType;
	}

	public void setBiaoType(String biaoType) {
		this.biaoType = biaoType;
	}

	public Integer getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Integer timeLimit) {
		this.timeLimit = timeLimit;
	}

	public Integer getStyle() {
		return style;
	}

	public void setStyle(Integer style) {
		this.style = style;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

}
