package cn.vfunding.common.framework.jms.activemq;

import cn.vfunding.common.framework.utils.beans.ObjectId;

import java.io.Serializable;

public class JmsSenderModelBuilder
{
  public static final String JMS_SYSTEM = "system";
  public static final String JMS_USERACTION = "useraction";

  public static JmsSenderModel buildUserActionJmsModel(String actionName, Serializable message)
  {
    JmsSenderModel model = new JmsSenderModel();
    model.setMessage(message);
    model.setJmsDestinationName("useraction");
    model.setMessageId(ObjectId.get());
    model.setActionName(actionName);
    return model;
  }

  public static JmsSenderModel buildSystemJmsModel(Serializable message) {
    JmsSenderModel model = new JmsSenderModel();
    model.setMessage(message);
    model.setMessageId(ObjectId.get());
    model.setJmsDestinationName("system");
    return model;
  }
}