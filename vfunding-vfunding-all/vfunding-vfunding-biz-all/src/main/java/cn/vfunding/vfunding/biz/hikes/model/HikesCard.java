package cn.vfunding.vfunding.biz.hikes.model;

import java.math.BigDecimal;

public class HikesCard {
    private Integer userId;

    private BigDecimal useRate;

    private BigDecimal noUseRate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getUseRate() {
        return useRate;
    }

    public void setUseRate(BigDecimal useRate) {
        this.useRate = useRate;
    }

    public BigDecimal getNoUseRate() {
        return noUseRate;
    }

    public void setNoUseRate(BigDecimal noUseRate) {
        this.noUseRate = noUseRate;
    }
}