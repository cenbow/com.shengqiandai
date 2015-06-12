package cn.vfunding.common.plat.sender.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.util.StringUtils;

/**
 * 邮件发送实体类
 * 
 * @author dct
 * 
 */
public class SendEmailVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3105552507950412752L;

	private String addressesString;
	// 发送目标地址
	private List<String> addresses;
	// 邮件标题
	private String title;
	// 邮件内容
	private String content;
	// 邮件类型
	private String emailType;

	// 发送时间
	private Date sendDate;
	// 发送状态

	private String status;

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

	public List<String> getAddresses() {
		List<String> list = null;
		if (StringUtils.hasText(addressesString)) {
			list = new ArrayList<String>();
			String[] ads = addressesString.split(";");
			for (String string : ads) {
				list.add(string);
			}
			this.addresses = list;
		}
		return addresses;
	}

	public void setAddresses(List<String> addresses) {
		this.addresses = addresses;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getEmailType() {
		return emailType;
	}

	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	public String getAddressesString() {
		return addressesString;
	}

	public void setAddressesString(String addressesString) {
		this.addressesString = addressesString;
	}

}
