package cn.vfunding.vfunding.biz.sina.model;

import java.math.BigDecimal;
import java.util.Date;

public class SinaSendActionLog {
    private Integer id;

    private Integer userId;

    private String action;

    private String responsemsg;

    private Date addtime;

    private BigDecimal balance;

    private BigDecimal availableBalance;

    private String bonus;

    private BigDecimal bonus1;

    private BigDecimal bonus2;

    private BigDecimal bonus3;

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

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action == null ? null : action.trim();
    }

    public String getResponsemsg() {
        return responsemsg;
    }

    public void setResponsemsg(String responsemsg) {
        this.responsemsg = responsemsg == null ? null : responsemsg.trim();
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getAvailableBalance() {
        return availableBalance;
    }

    public void setAvailableBalance(BigDecimal availableBalance) {
        this.availableBalance = availableBalance;
    }

    public String getBonus() {
        return bonus;
    }

    public void setBonus(String bonus) {
        this.bonus = bonus == null ? null : bonus.trim();
    }

    public BigDecimal getBonus1() {
        return bonus1;
    }

    public void setBonus1(BigDecimal bonus1) {
        this.bonus1 = bonus1;
    }

    public BigDecimal getBonus2() {
        return bonus2;
    }

    public void setBonus2(BigDecimal bonus2) {
        this.bonus2 = bonus2;
    }

    public BigDecimal getBonus3() {
        return bonus3;
    }

    public void setBonus3(BigDecimal bonus3) {
        this.bonus3 = bonus3;
    }
}