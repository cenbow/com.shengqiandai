package cn.vfunding.vfunding.biz.hikes.model;

import java.math.BigDecimal;
import java.util.Date;

public class HikesCardLog {
    private Integer id;

    private Integer userId;

    private Integer tenderId;

    private BigDecimal hikesRate;

    private Date addtime;

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

    public Integer getTenderId() {
        return tenderId;
    }

    public void setTenderId(Integer tenderId) {
        this.tenderId = tenderId;
    }

    public BigDecimal getHikesRate() {
        return hikesRate;
    }

    public void setHikesRate(BigDecimal hikesRate) {
        this.hikesRate = hikesRate;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}