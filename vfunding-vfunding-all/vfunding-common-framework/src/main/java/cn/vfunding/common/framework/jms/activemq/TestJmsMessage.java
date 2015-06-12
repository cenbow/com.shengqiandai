package cn.vfunding.common.framework.jms.activemq;

import java.io.Serializable;

public class TestJmsMessage
  implements Serializable
{
  private static final long serialVersionUID = 3920654601507450598L;
  private String name;

  public String getName()
  {
    return this.name;
  }

  public void setName(String name)
  {
    this.name = name;
  }
}