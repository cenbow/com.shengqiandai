package cn.p2p.p2p.biz.current.model;

import java.math.BigDecimal;

public class CurrentRule {
    private Integer currentId;

    private BigDecimal lowestAccount;

    private BigDecimal mostAccount;

    public Integer getCurrentId() {
        return currentId;
    }

    public void setCurrentId(Integer currentId) {
        this.currentId = currentId;
    }

    public BigDecimal getLowestAccount() {
        return lowestAccount;
    }

    public void setLowestAccount(BigDecimal lowestAccount) {
        this.lowestAccount = lowestAccount;
    }

    public BigDecimal getMostAccount() {
        return mostAccount;
    }

    public void setMostAccount(BigDecimal mostAccount) {
        this.mostAccount = mostAccount;
    }
}