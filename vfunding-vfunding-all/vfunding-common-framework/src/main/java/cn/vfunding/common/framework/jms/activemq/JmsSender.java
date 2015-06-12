package cn.vfunding.common.framework.jms.activemq;

import java.io.Serializable;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JmsSender {
	private JmsTemplate jmsTemplate;
	private boolean open=true;
	/**
	 * 同步消息
	 * @param object
	 */
	public void sendSynchronousMessage(final Serializable message) {
		if(open == true){
			sendMessage(message);
		}
    }
	
	/**
	 * 异步
	 * @param object
	 */
	public void sendAsynchronousMessage(final Serializable message){
		if(open == true){
			sendMessage(message);
		}
	}
	
	private void sendMessage(final Serializable message){
		jmsTemplate.send(new MessageCreator() {
            public ObjectMessage createMessage(Session session) throws JMSException {
            	ObjectMessage messge = session.createObjectMessage();
            	messge.setObject(message);
                return messge;
            }

        });
	}
	
	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
}
