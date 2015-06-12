package cn.vfunding.common.framework.jms.activemq;

import java.io.Serializable;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

public  abstract class  MessageReceiver implements InitializingBean {
	
	private Class<? extends Serializable> messageCls;
	private String messageClass;
	
	/**
	 * 接收消息的处理
	 * @param message
	 */
	public abstract void receive(Object message);
	
	@SuppressWarnings("unchecked")
    @Override
	public void afterPropertiesSet() throws Exception {
		if(!StringUtils.hasText(messageClass)){
			throw new RuntimeException("'messageClass' property must be set");
		}
		messageCls =  (Class<? extends Serializable>) Class.forName(messageClass);
	}

	public String getMessageClass() {
		return messageClass;
	}

	public void setMessageClass(String messageClass) {
		this.messageClass = messageClass;
	}

	public Class<? extends Serializable> getMessageCls() {
		return messageCls;
	}

}
