package cn.vfunding.vfunding.common.module.activemq.message;

@SuppressWarnings("serial")
public class BorrowAutoMessage extends BaseMessage {

	public BorrowAutoMessage(){
		super();
	}
	private Integer borrowId;

	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}
	
}
