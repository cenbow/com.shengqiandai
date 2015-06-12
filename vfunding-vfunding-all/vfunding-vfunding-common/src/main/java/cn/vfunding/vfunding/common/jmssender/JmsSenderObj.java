package cn.vfunding.vfunding.common.jmssender;

import java.io.Serializable;

public class JmsSenderObj implements Serializable{

	private static final long serialVersionUID = 6220723888445769348L;

	/**
	 * 消息对象的Class信息
	 */
	private Class<? extends Serializable> messageCls;
	/**
	 * 具体消息对象的json数据
	 */
	private String instanceJSON;
	/**
	 * 发送的通道
	 */
	private String jmsDestinationName;
		
	public String getInstanceJSON() {
		return instanceJSON;
	}

	public void setInstanceJSON(String instanceJSON) {
		this.instanceJSON = instanceJSON;
	}

	public String getJmsDestinationName() {
		return jmsDestinationName;
	}

	public void setJmsDestinationName(String jmsDestinationName) {
		this.jmsDestinationName = jmsDestinationName;
	}

	public Class<? extends Serializable> getMessageCls() {
		return messageCls;
	}

	public void setMessageCls(Class<? extends Serializable> messageCls) {
		this.messageCls = messageCls;
	}

	
	
	
}
