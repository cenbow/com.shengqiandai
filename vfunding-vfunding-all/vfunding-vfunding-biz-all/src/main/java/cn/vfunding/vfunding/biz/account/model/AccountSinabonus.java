package cn.vfunding.vfunding.biz.account.model;

import java.math.BigDecimal;
import java.util.Date;

public class AccountSinabonus {
    private Integer userId;

    private BigDecimal sinaBonusTotal;

    private BigDecimal sinaBonusPrevious;

    private Date updatetime;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public BigDecimal getSinaBonusTotal() {
        return sinaBonusTotal;
    }

    public void setSinaBonusTotal(BigDecimal sinaBonusTotal) {
        this.sinaBonusTotal = sinaBonusTotal;
    }

    public BigDecimal getSinaBonusPrevious() {
        return sinaBonusPrevious;
    }

    public void setSinaBonusPrevious(BigDecimal sinaBonusPrevious) {
        this.sinaBonusPrevious = sinaBonusPrevious;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}