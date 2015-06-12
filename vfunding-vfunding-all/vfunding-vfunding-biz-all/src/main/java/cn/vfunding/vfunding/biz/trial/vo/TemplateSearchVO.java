package cn.vfunding.vfunding.biz.trial.vo;

import cn.vfunding.common.framework.utils.beans.BaseVO;

import com.alibaba.druid.util.StringUtils;

@SuppressWarnings("serial")
public class TemplateSearchVO extends BaseVO{
	private String templateName;
	private Integer status;
	private String addTime;
	private String startTime;
	private String endTime;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getStartTime() {
		if(!StringUtils.isEmpty(addTime)){
			return addTime +" 00:00:00";
		}
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		if(!StringUtils.isEmpty(addTime)){
			return addTime +" 23:59:59";
		}
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getTemplateName() {
		return templateName;
	}
	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	
}
