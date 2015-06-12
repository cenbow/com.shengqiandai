package cn.vfunding.vfunding.biz.newyears.model;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityNewyear {
    private String phone;

    private BigDecimal hongbao;

    private BigDecimal hikes;

    private Date addtime;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}