package cn.vfunding.vfunding.biz.mobile.base.model;

import java.io.Serializable;

import cn.vfunding.vfunding.biz.mobile.service.IMobileResponseLogService;

public class MobileBaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String responseCode;
	private String responseMessage;
	private String orderNo;
	
	
	public MobileBaseResponse(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	public MobileBaseResponse() {
		super();
		this.responseCode = "success";
		this.responseMessage = "提交成功";
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
