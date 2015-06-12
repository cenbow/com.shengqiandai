package cn.vfunding.vfunding.common.module.activemq.message;

@SuppressWarnings("serial")
public class CjdBorrowMessage extends BaseMessage {

	
	public CjdBorrowMessage(){
		super();
	}
	private Integer borrowId;

	private Integer type;

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
	
	public static CjdBorrowMessage bulid(Integer type ,Integer borrowId){
		CjdBorrowMessage msg=new CjdBorrowMessage();
		msg.setType(type);
		msg.setBorrowId(borrowId);
		return msg;
	}

}
