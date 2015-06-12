package cn.vfunding.union.plat.mq.model;

import java.util.Date;

public class ProfitRecordInfo {
    private String id;

    private String unionUserId;

    private Integer investNum;

    private Float investMoney;

    private Float interests;

    private Date insertTime;

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

    public Integer getInvestNum() {
        return investNum;
    }

    public void setInvestNum(Integer investNum) {
        this.investNum = investNum;
    }

    public Float getInvestMoney() {
        return investMoney;
    }

    public void setInvestMoney(Float investMoney) {
        this.investMoney = investMoney;
    }

    public Float getInterests() {
        return interests;
    }

    public void setInterests(Float interests) {
        this.interests = interests;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}