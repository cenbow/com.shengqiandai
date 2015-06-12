package cn.vfunding.common.framework.jms.activemq;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.JmsUtils;

public class JmsReceiver implements MessageListener,ApplicationContextAware,InitializingBean {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private ApplicationContext applicationContext;
	
    private JmsTemplate jmsTemplate;
    private Destination destination;
    private List<MessageReceiver> messageReceiverList;
    @Override
	public void onMessage(Message message) {
    	try {  
	    	ObjectMessage objMsg = (ObjectMessage) message;  
	        if (null != objMsg) {  
	        	logger.debug("receiving message:" + objMsg.getClass().getName());
	        	Object msg = objMsg.getObject();
	        	for (MessageReceiver messageReceiver : messageReceiverList){
	        		if(messageReceiver.getMessageClass().equals(msg.getClass().getName())){
	        			logger.debug("pre process by:"+messageReceiver.getClass().getName());
	        			messageReceiver.receive(msg);
	        		}
	        	}
	        }
    	} catch (Exception e) {  
            e.printStackTrace();  
            throw new RuntimeException(e);
            //TODO
        }  
	}
	
    /**
     * 构造函数
     */
//    public void receive(){
//    	while (true) {  
//    		 
//    		           try {  
//    		        	   ObjectMessage objMsg = (ObjectMessage) jmsTemplate  
//    		                        .receive(destination);  
//    		                if (null != objMsg) {  
//    		                	logger.debug("receiving message:" + objMsg.getClass().getName());
//    		                	Object msg = objMsg.getObject();
//    		                	for (MessageReceiver messageReceiver : messageReceiverList){
//    		                		if(messageReceiver.getMessageClass().equals(msg.getClass().getName())){
//    		                			logger.debug("pre process by:"+messageReceiver.getClass().getName());
//    		                			messageReceiver.receive(msg);
//    		                		}
//    		                	}
//    		                } else  
//    		                    break;  
//    		            } catch (Exception e) {  
//    		                e.printStackTrace();  
//    		                throw new RuntimeException(e);
//    		                //TODO
//    		            }  
//    		  
//    		        }  
//
//    }

    public Object receiveMessage() {
        ObjectMessage message = (ObjectMessage) jmsTemplate.receive();
        try {
        	message.getObject();
        } catch (JMSException e) {
            throw JmsUtils.convertJmsAccessException(e);
        }
        return message;
    }
    
    @Override
	public void afterPropertiesSet() throws Exception {
    	messageReceiverList = new ArrayList<MessageReceiver>();
    	Map<String,MessageReceiver> messageReceiverMap = applicationContext.getBeansOfType(MessageReceiver.class);//MessageReceiver对象不能被动态代理 ，如声明式事务
    	for(Map.Entry<String,MessageReceiver> entry : messageReceiverMap.entrySet()){
    		messageReceiverList.add(entry.getValue());
    	}
	}
    
    //getter and setter
    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }
    
    @Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

    
	public List<MessageReceiver> getMessageReceiverList() {
		return messageReceiverList;
	}

	public Destination getDestination() {
		return destination;
	}

	public void setDestination(Destination destination) {
		this.destination = destination;
	}

	

}