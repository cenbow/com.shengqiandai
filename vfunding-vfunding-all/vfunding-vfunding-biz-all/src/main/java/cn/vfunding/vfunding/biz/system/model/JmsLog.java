package cn.vfunding.vfunding.biz.system.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class JmsLog extends BaseModel {

	public JmsLog() {
		super();
	}

	private String jmsId;

	private String jmsInfo;

	private String jmsCategory;

	private Date sendTime;

	private Date receiveTime;

	private Date completeTime;

	private Integer jmsStatus;

	private String jmsResult;

	public String getJmsId() {
		return jmsId;
	}

	public void setJmsId(String jmsId) {
		this.jmsId = jmsId == null ? null : jmsId.trim();
	}

	public String getJmsInfo() {
		return jmsInfo;
	}

	public void setJmsInfo(String jmsInfo) {
		this.jmsInfo = jmsInfo == null ? null : jmsInfo.trim();
	}

	public String getJmsCategory() {
		return jmsCategory;
	}

	public void setJmsCategory(String jmsCategory) {
		this.jmsCategory = jmsCategory == null ? null : jmsCategory.trim();
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Date getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Integer getJmsStatus() {
		return jmsStatus;
	}

	public void setJmsStatus(Integer jmsStatus) {
		this.jmsStatus = jmsStatus;
	}

	public Date getCompleteTime() {
		return completeTime;
	}

	public void setCompleteTime(Date completeTime) {
		this.completeTime = completeTime;
	}

	public String getJmsResult() {
		return jmsResult;
	}

	public void setJmsResult(String jmsResult) {
		this.jmsResult = jmsResult;
	}

}