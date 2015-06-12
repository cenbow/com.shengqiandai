package cn.p2p.p2p.biz.mobile.base.model;

import java.io.Serializable;

public class MobileBaseResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String responseCode;
	private String responseMessage;
	private String orderNo;
	private Object responseObject;
	
	public MobileBaseResponse(String responseCode, String responseMessage) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
	}
	
	public MobileBaseResponse(Object obj) {
		super();
		this.responseCode="success";
		this.responseMessage="提交成功";
		this.responseObject = obj;
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

	public Object getResponseObject() {
		return responseObject;
	}

	public void setResponseObject(Object responseObject) {
		this.responseObject = responseObject;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
