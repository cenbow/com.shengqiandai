package cn.vfunding.vfunding.biz.user.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserAmountapply extends BaseModel  {
    private Integer id;

    private Integer userId;

    private String type;

    private BigDecimal account;

    private BigDecimal accountNew;

    private BigDecimal accountOld;

    private Integer status;

    private String addtime;

    private String verifyRemark;

    private String verifyTime;

    private Integer verifyUser;

    private String addip;
    
    private Integer limit;//额度期限
    
    private Integer mortgagetypeId;//抵押品种id

    public Integer getLimit() {
		return limit;
	}

	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	public Integer getMortgagetypeId() {
		return mortgagetypeId;
	}

	public void setMortgagetypeId(Integer mortgagetypeId) {
		this.mortgagetypeId = mortgagetypeId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getAccountNew() {
        return accountNew;
    }

    public void setAccountNew(BigDecimal accountNew) {
        this.accountNew = accountNew;
    }

    public BigDecimal getAccountOld() {
        return accountOld;
    }

    public void setAccountOld(BigDecimal accountOld) {
        this.accountOld = accountOld;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    public String getVerifyRemark() {
        return verifyRemark;
    }

    public void setVerifyRemark(String verifyRemark) {
        this.verifyRemark = verifyRemark == null ? null : verifyRemark.trim();
    }

    public String getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(String verifyTime) {
        this.verifyTime = verifyTime == null ? null : verifyTime.trim();
    }

    public Integer getVerifyUser() {
        return verifyUser;
    }

    public void setVerifyUser(Integer verifyUser) {
        this.verifyUser = verifyUser;
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }
}