package cn.vfunding.vfunding.common.module.activemq.message.afteraction;

import cn.vfunding.common.framework.utils.beans.ObjectId;
import cn.vfunding.vfunding.common.module.activemq.message.BaseMessage;
/**
 * 动作后通知消息对象
 * @author liuyijun
 *
 */
@SuppressWarnings("serial")
public class AfterActionMessage extends BaseMessage {

	
	public AfterActionMessage(){
		super();
		this.messageId=ObjectId.get();
	}
	
	
	
	public AfterActionMessage(String actionName,Object[] methodArgs,Object methodReturn){
		super();
		this.actionName=actionName;
		//this.interceptorResource=resource;
		//this.method=method;
		this.methodArgs=methodArgs;
		this.methodReturn=methodReturn;
		this.messageId=ObjectId.get();
	}
	
	
	private String messageId;
	
	
	/**
	 * 动作名称
	 */
	private String actionName;
	
	
	/**
	 * 拦截的方法的参数
	 */
	private Object[] methodArgs;
	
	/**
	 * 拦截的方法的返回值
	 */
	private Object methodReturn;
	
	
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	public Object[] getMethodArgs() {
		return methodArgs;
	}

	public void setMethodArgs(Object[] methodArgs) {
		this.methodArgs = methodArgs;
	}

	public Object getMethodReturn() {
		return methodReturn;
	}

	public void setMethodReturn(Object methodReturn) {
		this.methodReturn = methodReturn;
	}



	public String getMessageId() {
		return messageId;
	}



	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	
	
	
	

}
