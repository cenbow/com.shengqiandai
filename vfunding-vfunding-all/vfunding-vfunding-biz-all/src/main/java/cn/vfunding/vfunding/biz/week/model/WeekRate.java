package cn.vfunding.vfunding.biz.week.model;

import java.math.BigDecimal;
import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class WeekRate extends BaseModel{
    private Integer weekId;

    private BigDecimal weekRate;

    private BigDecimal platformRate;

    private BigDecimal guaranteeRate;

    private Date addTime;

    public Integer getWeekId() {
        return weekId;
    }

    public void setWeekId(Integer weekId) {
        this.weekId = weekId;
    }

    public BigDecimal getWeekRate() {
        return weekRate;
    }

    public void setWeekRate(BigDecimal weekRate) {
        this.weekRate = weekRate;
    }

    public BigDecimal getPlatformRate() {
        return platformRate;
    }

    public void setPlatformRate(BigDecimal platformRate) {
        this.platformRate = platformRate;
    }

    public BigDecimal getGuaranteeRate() {
        return guaranteeRate;
    }

    public void setGuaranteeRate(BigDecimal guaranteeRate) {
        this.guaranteeRate = guaranteeRate;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
}