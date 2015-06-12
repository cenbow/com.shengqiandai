package cn.vfunding.vfunding.biz.achievement.model;

import java.math.BigDecimal;
import java.util.Date;

public class Achievement {
    private Integer id;

    private String name;

    private String squad;

    private String type;

    private BigDecimal condition;

    private String award;

    private BigDecimal awardValue;

    private Date addtime;

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

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad == null ? null : squad.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public BigDecimal getCondition() {
        return condition;
    }

    public void setCondition(BigDecimal condition) {
        this.condition = condition;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award == null ? null : award.trim();
    }

    public BigDecimal getAwardValue() {
        return awardValue;
    }

    public void setAwardValue(BigDecimal awardValue) {
        this.awardValue = awardValue;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}