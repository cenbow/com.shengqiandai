package cn.vfunding.vfunding.biz.prize.model;

import java.util.Date;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class PrizeChooseRuleLog extends BaseModel {
    private Integer id;

    private Integer userId;

    private Integer type;

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}