package cn.vfunding.vfunding.biz.user.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class UserAmount extends BaseModel  {
    private Integer id;

    private Integer userId;

    private BigDecimal credit;

    private BigDecimal creditUse;

    private BigDecimal creditNouse;

    private BigDecimal borrowVouch;

    private BigDecimal borrowVouchUse;

    private BigDecimal borrowVouchNouse;

    private BigDecimal tenderVouch;

    private BigDecimal tenderVouchUse;

    private BigDecimal tenderVouchNouse;

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

    public BigDecimal getCredit() {
        return credit;
    }

    public void setCredit(BigDecimal credit) {
        this.credit = credit;
    }

    public BigDecimal getCreditUse() {
        return creditUse;
    }

    public void setCreditUse(BigDecimal creditUse) {
        this.creditUse = creditUse;
    }

    public BigDecimal getCreditNouse() {
        return creditNouse;
    }

    public void setCreditNouse(BigDecimal creditNouse) {
        this.creditNouse = creditNouse;
    }

    public BigDecimal getBorrowVouch() {
        return borrowVouch;
    }

    public void setBorrowVouch(BigDecimal borrowVouch) {
        this.borrowVouch = borrowVouch;
    }

    public BigDecimal getBorrowVouchUse() {
        return borrowVouchUse;
    }

    public void setBorrowVouchUse(BigDecimal borrowVouchUse) {
        this.borrowVouchUse = borrowVouchUse;
    }

    public BigDecimal getBorrowVouchNouse() {
        return borrowVouchNouse;
    }

    public void setBorrowVouchNouse(BigDecimal borrowVouchNouse) {
        this.borrowVouchNouse = borrowVouchNouse;
    }

    public BigDecimal getTenderVouch() {
        return tenderVouch;
    }

    public void setTenderVouch(BigDecimal tenderVouch) {
        this.tenderVouch = tenderVouch;
    }

    public BigDecimal getTenderVouchUse() {
        return tenderVouchUse;
    }

    public void setTenderVouchUse(BigDecimal tenderVouchUse) {
        this.tenderVouchUse = tenderVouchUse;
    }

    public BigDecimal getTenderVouchNouse() {
        return tenderVouchNouse;
    }

    public void setTenderVouchNouse(BigDecimal tenderVouchNouse) {
        this.tenderVouchNouse = tenderVouchNouse;
    }
}