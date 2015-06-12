package cn.p2p.p2p.biz.current.model;

import java.math.BigDecimal;

public class CurrentUserAccount {
    private Integer userId;

    private BigDecimal total;

    private BigDecimal useMoney;

    private BigDecimal noUseMoney;

    private BigDecimal useInterest;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BigDecimal getUseMoney() {
        return useMoney;
    }

    public void setUseMoney(BigDecimal useMoney) {
        this.useMoney = useMoney;
    }

    public BigDecimal getNoUseMoney() {
        return noUseMoney;
    }

    public void setNoUseMoney(BigDecimal noUseMoney) {
        this.noUseMoney = noUseMoney;
    }

    public BigDecimal getUseInterest() {
        return useInterest;
    }

    public void setUseInterest(BigDecimal useInterest) {
        this.useInterest = useInterest;
    }
}