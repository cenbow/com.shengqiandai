package cn.vfunding.vfunding.biz.account.model;

import java.math.BigDecimal;

import cn.vfunding.vfunding.common.model.BaseModel;
@SuppressWarnings("serial")
public class Account extends BaseModel{
    private Integer id;

    private Integer userId;

    private BigDecimal total;

    private BigDecimal useMoney;

    private BigDecimal noUseMoney;

    private BigDecimal collection;

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

    public BigDecimal getCollection() {
        return collection;
    }

    public void setCollection(BigDecimal collection) {
        this.collection = collection;
    }
}