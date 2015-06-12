package cn.vfunding.vfunding.common.module.activemq.message;

@SuppressWarnings("serial")
public class CjdTenderMessage extends BaseMessage {
	
	public CjdTenderMessage(){
		super();
	}

	private Integer userId;

	private Integer tenderId;

	private Integer borrowId;

	private Integer repaymentId;

	private Integer type;

	private Integer tenderStatus;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTenderId() {
		return tenderId;
	}

	public void setTenderId(Integer tenderId) {
		this.tenderId = tenderId;
	}

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(Integer repaymentId) {
		this.repaymentId = repaymentId;
	}

	public Integer getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(Integer tenderStatus) {
		this.tenderStatus = tenderStatus;
	}

	public static CjdTenderMessage bulid(Integer type, Integer borrowId) {
		CjdTenderMessage msg = new CjdTenderMessage();
		msg.setType(type);
		msg.setBorrowId(borrowId);
		return msg;
	}
	
	public static CjdTenderMessage bulidByRepaymentId(Integer type, Integer repaymentId) {
		CjdTenderMessage msg = new CjdTenderMessage();
		msg.setType(type);
		msg.setRepaymentId(repaymentId);
		return msg;
	}

}
