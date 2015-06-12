package cn.vfunding.vfunding.prd.bms.appParm.controller;

import java.util.List;

import cn.vfunding.vfunding.biz.system.model.ApplicationParm;

public class ApplicationParmDataGridVO {

	private List<ApplicationParm> inserted;
	
	private List<ApplicationParm> deleted;
	
	private List<ApplicationParm> updated;

	public List<ApplicationParm> getInserted() {
		return inserted;
	}

	public void setInserted(List<ApplicationParm> inserted) {
		this.inserted = inserted;
	}

	public List<ApplicationParm> getDeleted() {
		return deleted;
	}

	public void setDeleted(List<ApplicationParm> deleted) {
		this.deleted = deleted;
	}

	public List<ApplicationParm> getUpdated() {
		return updated;
	}

	public void setUpdated(List<ApplicationParm> updated) {
		this.updated = updated;
	}

	

	
}
