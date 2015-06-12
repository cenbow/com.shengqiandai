package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class Payment extends BaseModel  {
    private Integer id;

    private String name;

    private String nid;

    private Boolean status;

    private Integer style;

    private Boolean feeType;

    private Integer maxMoney;

    private Float maxFee;

    private Byte order;

    private Boolean reward;

    private Boolean rewardType;

    private Integer rewardWhere;

    private Float rewardBl;

    private Integer rewardEd;

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

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid == null ? null : nid.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getStyle() {
        return style;
    }

    public void setStyle(Integer style) {
        this.style = style;
    }

    public Boolean getFeeType() {
        return feeType;
    }

    public void setFeeType(Boolean feeType) {
        this.feeType = feeType;
    }

    public Integer getMaxMoney() {
        return maxMoney;
    }

    public void setMaxMoney(Integer maxMoney) {
        this.maxMoney = maxMoney;
    }

    public Float getMaxFee() {
        return maxFee;
    }

    public void setMaxFee(Float maxFee) {
        this.maxFee = maxFee;
    }

    public Byte getOrder() {
        return order;
    }

    public void setOrder(Byte order) {
        this.order = order;
    }

    public Boolean getReward() {
        return reward;
    }

    public void setReward(Boolean reward) {
        this.reward = reward;
    }

    public Boolean getRewardType() {
        return rewardType;
    }

    public void setRewardType(Boolean rewardType) {
        this.rewardType = rewardType;
    }

    public Integer getRewardWhere() {
        return rewardWhere;
    }

    public void setRewardWhere(Integer rewardWhere) {
        this.rewardWhere = rewardWhere;
    }

    public Float getRewardBl() {
        return rewardBl;
    }

    public void setRewardBl(Float rewardBl) {
        this.rewardBl = rewardBl;
    }

    public Integer getRewardEd() {
        return rewardEd;
    }

    public void setRewardEd(Integer rewardEd) {
        this.rewardEd = rewardEd;
    }
}