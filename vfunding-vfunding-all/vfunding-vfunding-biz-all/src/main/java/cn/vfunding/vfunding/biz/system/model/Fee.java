package cn.vfunding.vfunding.biz.system.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Fee extends  BaseModel{
    private Integer id;

    private String name;

    private BigDecimal apr;

    private BigDecimal bfee;

    private BigDecimal gfee;

    private Integer timeLimit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public BigDecimal getApr() {
        return apr;
    }

    public void setApr(BigDecimal apr) {
        this.apr = apr;
    }

    public BigDecimal getBfee() {
        return bfee;
    }

    public void setBfee(BigDecimal bfee) {
        this.bfee = bfee;
    }

    public BigDecimal getGfee() {
        return gfee;
    }

    public void setGfee(BigDecimal gfee) {
        this.gfee = gfee;
    }

    public Integer getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Integer timeLimit) {
        this.timeLimit = timeLimit;
    }
}