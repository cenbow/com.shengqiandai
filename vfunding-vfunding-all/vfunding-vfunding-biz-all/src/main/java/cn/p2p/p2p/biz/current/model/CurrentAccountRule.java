package cn.p2p.p2p.biz.current.model;

import java.math.BigDecimal;

public class CurrentAccountRule {
    private Integer userId;

    private BigDecimal mostHoldMoney;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getMostHoldMoney() {
        return mostHoldMoney;
    }

    public void setMostHoldMoney(BigDecimal mostHoldMoney) {
        this.mostHoldMoney = mostHoldMoney;
    }
}