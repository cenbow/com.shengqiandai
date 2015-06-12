package cn.vfunding.common.framework.send.util;

import cn.vfunding.common.framework.utils.beans.BaseVO;

public class SendCommon extends BaseVO
{
  private String type = "sms";
  private String sendMessageKey;
  private String[] params;
  private String phone;
  private String email;

  public SendCommon()
  {
  }

  public SendCommon(String sendMessageKey, String[] params)
  {
    this.sendMessageKey = sendMessageKey;
    this.params = params;
  }

  public String getType()
  {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSendMessageKey() {
    return this.sendMessageKey;
  }

  public void setSendMessageKey(String sendMessageKey) {
    this.sendMessageKey = sendMessageKey;
  }

  public String[] getParams() {
    return this.params;
  }

  public void setParams(String[] params) {
    this.params = params;
  }

  public String getPhone()
  {
    return this.phone;
  }

  public void setPhone(String phone)
  {
    this.phone = phone;
  }

  public String getEmail()
  {
    return this.email;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }
}