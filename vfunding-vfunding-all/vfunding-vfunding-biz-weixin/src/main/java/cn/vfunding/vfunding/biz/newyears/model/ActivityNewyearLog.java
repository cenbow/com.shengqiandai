package cn.vfunding.vfunding.biz.newyears.model;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityNewyearLog {
    private Integer id;

    private String phone;

    private String fromPhone;

    private BigDecimal hongbao;

    private BigDecimal hikes;

    private Integer status;

    private Date addtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getFromPhone() {
        return fromPhone;
    }

    public void setFromPhone(String fromPhone) {
        this.fromPhone = fromPhone == null ? null : fromPhone.trim();
    }

    public BigDecimal getHongbao() {
        return hongbao;
    }

    public void setHongbao(BigDecimal hongbao) {
        this.hongbao = hongbao;
    }

    public BigDecimal getHikes() {
        return hikes;
    }

    public void setHikes(BigDecimal hikes) {
        this.hikes = hikes;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}