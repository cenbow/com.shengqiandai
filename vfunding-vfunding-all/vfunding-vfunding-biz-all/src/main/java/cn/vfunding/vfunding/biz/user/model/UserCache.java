package cn.vfunding.vfunding.biz.user.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserCache extends BaseModel  {
    private Integer userId;

    private Integer kefuUserid;

    private Integer kefuUsername;

    private Integer kefuAddtime;

    private Integer vipStatus;

    private String vipRemark;

    private String vipMoney;

    private String vipVerifyRemark;

    private String vipVerifyTime;

    private Integer bbsTopicsNum;

    private Integer bbsPostsNum;

    private Integer credit;

    private Integer account;

    private Integer accountUse;

    private Integer accountNouse;

    private Integer accountWaitin;

    private Integer accountWaitintrest;

    private Integer accountIntrest;

    private Integer accountAward;

    private Integer accountPayment;

    private Integer accountExpired;

    private Integer accountWaitvip;

    private Integer borrowAmount;

    private Integer vouchAmount;

    private Integer borrowLoan;

    private Integer borrowSuccess;

    private Integer borrowWait;

    private Integer borrowPaymeng;

    private Integer friendsApply;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getKefuUserid() {
        return kefuUserid;
    }

    public void setKefuUserid(Integer kefuUserid) {
        this.kefuUserid = kefuUserid;
    }

    public Integer getKefuUsername() {
        return kefuUsername;
    }

    public void setKefuUsername(Integer kefuUsername) {
        this.kefuUsername = kefuUsername;
    }

    public Integer getKefuAddtime() {
        return kefuAddtime;
    }

    public void setKefuAddtime(Integer kefuAddtime) {
        this.kefuAddtime = kefuAddtime;
    }

    public Integer getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(Integer vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getVipRemark() {
        return vipRemark;
    }

    public void setVipRemark(String vipRemark) {
        this.vipRemark = vipRemark == null ? null : vipRemark.trim();
    }

    public String getVipMoney() {
        return vipMoney;
    }

    public void setVipMoney(String vipMoney) {
        this.vipMoney = vipMoney == null ? null : vipMoney.trim();
    }

    public String getVipVerifyRemark() {
        return vipVerifyRemark;
    }

    public void setVipVerifyRemark(String vipVerifyRemark) {
        this.vipVerifyRemark = vipVerifyRemark == null ? null : vipVerifyRemark.trim();
    }

    public String getVipVerifyTime() {
        return vipVerifyTime;
    }

    public void setVipVerifyTime(String vipVerifyTime) {
        this.vipVerifyTime = vipVerifyTime == null ? null : vipVerifyTime.trim();
    }

    public Integer getBbsTopicsNum() {
        return bbsTopicsNum;
    }

    public void setBbsTopicsNum(Integer bbsTopicsNum) {
        this.bbsTopicsNum = bbsTopicsNum;
    }

    public Integer getBbsPostsNum() {
        return bbsPostsNum;
    }

    public void setBbsPostsNum(Integer bbsPostsNum) {
        this.bbsPostsNum = bbsPostsNum;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }

    public Integer getAccountUse() {
        return accountUse;
    }

    public void setAccountUse(Integer accountUse) {
        this.accountUse = accountUse;
    }

    public Integer getAccountNouse() {
        return accountNouse;
    }

    public void setAccountNouse(Integer accountNouse) {
        this.accountNouse = accountNouse;
    }

    public Integer getAccountWaitin() {
        return accountWaitin;
    }

    public void setAccountWaitin(Integer accountWaitin) {
        this.accountWaitin = accountWaitin;
    }

    public Integer getAccountWaitintrest() {
        return accountWaitintrest;
    }

    public void setAccountWaitintrest(Integer accountWaitintrest) {
        this.accountWaitintrest = accountWaitintrest;
    }

    public Integer getAccountIntrest() {
        return accountIntrest;
    }

    public void setAccountIntrest(Integer accountIntrest) {
        this.accountIntrest = accountIntrest;
    }

    public Integer getAccountAward() {
        return accountAward;
    }

    public void setAccountAward(Integer accountAward) {
        this.accountAward = accountAward;
    }

    public Integer getAccountPayment() {
        return accountPayment;
    }

    public void setAccountPayment(Integer accountPayment) {
        this.accountPayment = accountPayment;
    }

    public Integer getAccountExpired() {
        return accountExpired;
    }

    public void setAccountExpired(Integer accountExpired) {
        this.accountExpired = accountExpired;
    }

    public Integer getAccountWaitvip() {
        return accountWaitvip;
    }

    public void setAccountWaitvip(Integer accountWaitvip) {
        this.accountWaitvip = accountWaitvip;
    }

    public Integer getBorrowAmount() {
        return borrowAmount;
    }

    public void setBorrowAmount(Integer borrowAmount) {
        this.borrowAmount = borrowAmount;
    }

    public Integer getVouchAmount() {
        return vouchAmount;
    }

    public void setVouchAmount(Integer vouchAmount) {
        this.vouchAmount = vouchAmount;
    }

    public Integer getBorrowLoan() {
        return borrowLoan;
    }

    public void setBorrowLoan(Integer borrowLoan) {
        this.borrowLoan = borrowLoan;
    }

    public Integer getBorrowSuccess() {
        return borrowSuccess;
    }

    public void setBorrowSuccess(Integer borrowSuccess) {
        this.borrowSuccess = borrowSuccess;
    }

    public Integer getBorrowWait() {
        return borrowWait;
    }

    public void setBorrowWait(Integer borrowWait) {
        this.borrowWait = borrowWait;
    }

    public Integer getBorrowPaymeng() {
        return borrowPaymeng;
    }

    public void setBorrowPaymeng(Integer borrowPaymeng) {
        this.borrowPaymeng = borrowPaymeng;
    }

    public Integer getFriendsApply() {
        return friendsApply;
    }

    public void setFriendsApply(Integer friendsApply) {
        this.friendsApply = friendsApply;
    }
}