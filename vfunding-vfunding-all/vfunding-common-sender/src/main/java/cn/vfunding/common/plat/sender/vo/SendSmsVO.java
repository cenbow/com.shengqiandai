 package cn.vfunding.common.plat.sender.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 短信发送PO类
 * @author dct
 *
 */
public class SendSmsVO implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3105552507950412752L;
	//发送号码
	private List<String> mobiles;
	//接收者类型
	private String receiverType;
	
	//短信内容
	private String  content;
	//短信类型
	private String bizType;
	//调用方id
	private String bizId;
	//发送时间
	private Date sendDate;
	//发送状态
	private String status;
	//扩展号码
	private String addSerial;
	//优先级
	private Integer smsPriority=1;
	public String getReceiverType() {
		return receiverType;
	}
	public void setReceiverType(String receiverType) {
		this.receiverType = receiverType;
	}
	public List<String> getMobiles() {
		return mobiles;
	}
	public void setMobiles(List<String> mobiles) {
		this.mobiles = mobiles;
	}
	public String getAddSerial() {
		return addSerial;
	}
	public void setAddSerial(String addSerial) {
		this.addSerial = addSerial;
	}
	public Integer getSmsPriority() {
		return smsPriority;
	}
	public void setSmsPriority(Integer smsPriority) {
		this.smsPriority = smsPriority;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getBizType() {
		return bizType;
	}
	public void setBizType(String bizType) {
		this.bizType = bizType;
	}
	public String getBizId() {
		return bizId;
	}
	public void setBizId(String bizId) {
		this.bizId = bizId;
	}
	
}
