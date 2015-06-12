package cn.vfunding.common.framework.jms.activemq;

import java.io.Serializable;

public class JmsSenderModel
  implements Serializable
{
  private static final long serialVersionUID = 969592949077097483L;
  private String messageId;
  private String actionName;
  private String jmsDestinationName;
  private Serializable message;

  public Serializable getMessage()
  {
    return this.message;
  }

  public void setMessage(Serializable message) {
    this.message = message;
  }

  public String getMessageId() {
    return this.messageId;
  }

  public void setMessageId(String messageId) {
    this.messageId = messageId;
  }

  public String getActionName() {
    return this.actionName;
  }

  public void setActionName(String actionName) {
    this.actionName = actionName;
  }

  public String getJmsDestinationName() {
    return this.jmsDestinationName;
  }

  public void setJmsDestinationName(String jmsDestinationName) {
    this.jmsDestinationName = jmsDestinationName;
  }
}