package cn.vfunding.vfunding.biz.inviteCode.model;

public class InviteCode {
    private Integer id;

    private Integer userId;

    private String inviteCode;
    
    private Integer dialogStatus;

	public Integer getDialogStatus() {
		return dialogStatus;
	}

	public void setDialogStatus(Integer dialogStatus) {
		this.dialogStatus = dialogStatus;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getInviteCode() {
        return inviteCode;
    }

    public void setInviteCode(String inviteCode) {
        this.inviteCode = inviteCode == null ? null : inviteCode.trim();
    }
}