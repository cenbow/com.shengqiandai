package cn.vfunding.common.plat.sender.model;

import java.io.Serializable;



public class Attachment  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3906317702582256473L;
	private String attachmentName;
	private byte[] attachmentData;
	
	
	public byte[] getAttachmentData() {
		return attachmentData;
	}

	public void setAttachmentData(byte[] attachmentData) {
		this.attachmentData = attachmentData;
	}

	public String getAttachmentName() {
		return attachmentName;
	}

	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}

	
}
