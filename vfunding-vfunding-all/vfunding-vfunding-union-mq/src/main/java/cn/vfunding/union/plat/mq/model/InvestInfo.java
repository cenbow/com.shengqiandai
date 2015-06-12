package cn.vfunding.union.plat.mq.model;

import java.util.Date;

public class InvestInfo {
    private String id;

    private String unionUserId;

    private String productId;

    private Integer investId;

    private String investName;

    private Date investTime;

    private Float investMoney;

    private Integer periods;

    private Float interests;

    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUnionUserId() {
        return unionUserId;
    }

    public void setUnionUserId(String unionUserId) {
        this.unionUserId = unionUserId == null ? null : unionUserId.trim();
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId == null ? null : productId.trim();
    }

    public Integer getInvestId() {
        return investId;
    }

    public void setInvestId(Integer investId) {
        this.investId = investId;
    }

    public String getInvestName() {
        return investName;
    }

    public void setInvestName(String investName) {
        this.investName = investName == null ? null : investName.trim();
    }

    public Date getInvestTime() {
        return investTime;
    }

    public void setInvestTime(Date investTime) {
        this.investTime = investTime;
    }

    public Float getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(Float investMoney) {
        this.investMoney = investMoney;
    }

    public Integer getPeriods() {
        return periods;
    }

    public void setPeriods(Integer periods) {
        this.periods = periods;
    }

    public Float getInterests() {
        return interests;
    }

    public void setInterests(Float interests) {
        this.interests = interests;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}