package cn.vfunding.vfunding.biz.borrow.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowAmountlog extends  BaseModel{
    private Integer id;

    private Integer userId;

    private String type;

    private String amountType;

    private BigDecimal account;

    private BigDecimal accountTotal;

    private BigDecimal accountUse;

    private BigDecimal accountNouse;

    private String remark;

    private String addtime;

    private String addip;

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

    public String getAmountType() {
        return amountType;
    }

    public void setAmountType(String amountType) {
        this.amountType = amountType == null ? null : amountType.trim();
    }

    public BigDecimal getAccount() {
        return account;
    }

    public void setAccount(BigDecimal account) {
        this.account = account;
    }

    public BigDecimal getAccountTotal() {
        return accountTotal;
    }

    public void setAccountTotal(BigDecimal accountTotal) {
        this.accountTotal = accountTotal;
    }

    public BigDecimal getAccountUse() {
        return accountUse;
    }

    public void setAccountUse(BigDecimal accountUse) {
        this.accountUse = accountUse;
    }

    public BigDecimal getAccountNouse() {
        return accountNouse;
    }

    public void setAccountNouse(BigDecimal accountNouse) {
        this.accountNouse = accountNouse;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
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
}