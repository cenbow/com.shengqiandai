package cn.p2p.p2p.biz.current.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class CurrentRedeemRule extends BaseModel{
    private Integer userId;

    private BigDecimal dayMostAccount;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getDayMostAccount() {
        return dayMostAccount;
    }

    public void setDayMostAccount(BigDecimal dayMostAccount) {
        this.dayMostAccount = dayMostAccount;
    }
}