package cn.vfunding.vfunding.common.module.activemq.message;

@SuppressWarnings("serial")
public class CjdUserMessage extends BaseMessage {
	
	
	public CjdUserMessage(){
		super();
	}
	private String uaccount;
	
	private Integer userId;
	
	private Integer type;
	
	private String repaymentId;
	
	private Integer borrowId;

	
	public Integer getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(Integer borrowId) {
		this.borrowId = borrowId;
	}

	public String getRepaymentId() {
		return repaymentId;
	}

	public void setRepaymentId(String repaymentId) {
		this.repaymentId = repaymentId;
	}

	public String getUaccount() {
		return uaccount;
	}

	public void setUaccount(String uaccount) {
		this.uaccount = uaccount;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
	
	
	public static CjdUserMessage bulid(Integer type,Integer userId){
		CjdUserMessage msg=new CjdUserMessage();
		msg.setType(type);
		msg.setUserId(userId);
		return msg;
	}
	
	public static CjdUserMessage bulidByBorrowId(Integer type,Integer borrowId){
		CjdUserMessage msg=new CjdUserMessage();
		msg.setType(type);
		msg.setBorrowId(borrowId);
		return msg;
	}
	
	public static CjdUserMessage bulidByType(Integer type){
		CjdUserMessage msg=new CjdUserMessage();
		msg.setType(type);
		return msg;
	}
	
}
