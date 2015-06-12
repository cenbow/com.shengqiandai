package cn.vfunding.vfunding.biz.sina.vo;

public class SinaSendLogVO {
	
	/**
	 * 订单编号或用户ID
	 * 非空
	 */
	private String no;
	/**
	 * 请求状态 0,未成功  1重试后成功,2成功
	 * 非空
	 */
	private Integer status;
	/**
	 * 重试次数 可空
	 */
	private Integer retry = 0;
	/**
	 * 接口名称  非空
	 */
	private String interfaceName; 
	/**
	 * 发送对象  非空
	 */
	private Object args;
	/**
	 * 响应对象  可空
	 */
	private Object responseObj;
	
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getRetry() {
		return retry;
	}
	public void setRetry(Integer retry) {
		this.retry = retry;
	}
	public String getInterfaceName() {
		return interfaceName;
	}
	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}
	public Object getArgs() {
		return args;
	}
	public void setArgs(Object args) {
		this.args = args;
	}
	public Object getResponseObj() {
		return responseObj;
	}
	public void setResponseObj(Object responseObj) {
		this.responseObj = responseObj;
	}
	
	
}
