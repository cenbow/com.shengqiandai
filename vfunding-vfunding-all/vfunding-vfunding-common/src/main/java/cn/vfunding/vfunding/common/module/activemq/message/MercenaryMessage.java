package cn.vfunding.vfunding.common.module.activemq.message;


@SuppressWarnings("serial")
public class MercenaryMessage extends BaseMessage {

	public MercenaryMessage(){
		super();
	}
	private Integer borrowId;

	private String ip;

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
	

}
