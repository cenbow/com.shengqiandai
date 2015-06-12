package cn.vfunding.vfunding.biz.borrow.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowVouch extends  BaseModel{
    private Integer id;

    private Integer userId;

    private Integer borrowId;

    private Integer status;

    private String account;

    private BigDecimal vouchAccount;

    private String awardFund;

    private BigDecimal awardAccount;

    private String addtime;

    private String addip;

    private String content;

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

    public Integer getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(Integer borrowId) {
        this.borrowId = borrowId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public BigDecimal getVouchAccount() {
        return vouchAccount;
    }

    public void setVouchAccount(BigDecimal vouchAccount) {
        this.vouchAccount = vouchAccount;
    }

    public String getAwardFund() {
        return awardFund;
    }

    public void setAwardFund(String awardFund) {
        this.awardFund = awardFund == null ? null : awardFund.trim();
    }

    public BigDecimal getAwardAccount() {
        return awardAccount;
    }

    public void setAwardAccount(BigDecimal awardAccount) {
        this.awardAccount = awardAccount;
    }

    public String getAddtime() {
        return addtime;
    }

    public void setAddtime(String addtime) {
        this.addtime = addtime == null ? null : addtime.trim();
    }

    public String getAddip() {
        return addip;
    }

    public void setAddip(String addip) {
        this.addip = addip == null ? null : addip.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }
}