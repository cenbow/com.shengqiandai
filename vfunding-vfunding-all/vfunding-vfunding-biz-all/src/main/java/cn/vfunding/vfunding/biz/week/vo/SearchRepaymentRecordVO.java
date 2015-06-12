package cn.vfunding.vfunding.biz.week.vo;

import java.util.List;

import cn.vfunding.common.framework.utils.beans.BaseVO;
import cn.vfunding.vfunding.biz.week.model.Week;

@SuppressWarnings("serial")
public class SearchRepaymentRecordVO extends BaseVO {
	private Integer id;
	private String time;
	private String weekIds;
	private Integer status;
	private String startTime;
	private String endTime;
	private String name;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getWeekIds() {
		return weekIds;
	}
	public void setWeekIds(List<Week> wList) {
		if(!wList.isEmpty()){
			String weekIds = "";
			for(int i = 0;i< wList.size();i++){
				if(i == wList.size()-1){
					weekIds += wList.get(i).getId();
				}else{
					weekIds += wList.get(i).getId() + ",";
				}
			}
			this.weekIds = weekIds;
		}else{
			this.weekIds = null;
		}
		
	}
	public String getStartTime() {
		return time+" 00:00:00";
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return time+" 23:59:59";
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
}
