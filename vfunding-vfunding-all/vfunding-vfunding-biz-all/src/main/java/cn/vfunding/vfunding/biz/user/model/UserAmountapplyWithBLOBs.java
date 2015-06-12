package cn.vfunding.vfunding.biz.user.model;

public class UserAmountapplyWithBLOBs extends UserAmountapply {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String content;

	private String remark;

	private String username;

	private String realname;
	private String phone;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content == null ? null : content.trim();
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
	}
}