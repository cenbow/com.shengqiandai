package cn.vfunding.vfunding.biz.system.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class CashRule extends  BaseModel{
    private Byte id;

    private Integer maxCash;

    private Integer minCash;

    private Integer maxDayCash;

    private String scheme;

    private Integer cashLt;

    private Short everyLtFee;

    private Integer cashGt;

    private Short everyGtFee;

    private Byte everyDayLt;

    private Short everyExtraFee;

    private Float scaleFee;

    private Byte scaleDayLt;

    private Float scaleExtraFee;

    private String status;

    public Byte getId() {
        return id;
    }

    public void setId(Byte id) {
        this.id = id;
    }

    public Integer getMaxCash() {
        return maxCash;
    }

    public void setMaxCash(Integer maxCash) {
        this.maxCash = maxCash;
    }

    public Integer getMinCash() {
        return minCash;
    }

    public void setMinCash(Integer minCash) {
        this.minCash = minCash;
    }

    public Integer getMaxDayCash() {
        return maxDayCash;
    }

    public void setMaxDayCash(Integer maxDayCash) {
        this.maxDayCash = maxDayCash;
    }

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme == null ? null : scheme.trim();
    }

    public Integer getCashLt() {
        return cashLt;
    }

    public void setCashLt(Integer cashLt) {
        this.cashLt = cashLt;
    }

    public Short getEveryLtFee() {
        return everyLtFee;
    }

    public void setEveryLtFee(Short everyLtFee) {
        this.everyLtFee = everyLtFee;
    }

    public Integer getCashGt() {
        return cashGt;
    }

    public void setCashGt(Integer cashGt) {
        this.cashGt = cashGt;
    }

    public Short getEveryGtFee() {
        return everyGtFee;
    }

    public void setEveryGtFee(Short everyGtFee) {
        this.everyGtFee = everyGtFee;
    }

    public Byte getEveryDayLt() {
        return everyDayLt;
    }

    public void setEveryDayLt(Byte everyDayLt) {
        this.everyDayLt = everyDayLt;
    }

    public Short getEveryExtraFee() {
        return everyExtraFee;
    }

    public void setEveryExtraFee(Short everyExtraFee) {
        this.everyExtraFee = everyExtraFee;
    }

    public Float getScaleFee() {
        return scaleFee;
    }

    public void setScaleFee(Float scaleFee) {
        this.scaleFee = scaleFee;
    }

    public Byte getScaleDayLt() {
        return scaleDayLt;
    }

    public void setScaleDayLt(Byte scaleDayLt) {
        this.scaleDayLt = scaleDayLt;
    }

    public Float getScaleExtraFee() {
        return scaleExtraFee;
    }

    public void setScaleExtraFee(Float scaleExtraFee) {
        this.scaleExtraFee = scaleExtraFee;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}