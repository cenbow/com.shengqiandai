package cn.vfunding.vfunding.biz.user.model;

import java.util.Date;

public class UserIntroduce {
    private Integer id;

    private Integer userId;

    private Integer status;

    private Date introduceTime;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getIntroduceTime() {
        return introduceTime;
    }

    public void setIntroduceTime(Date introduceTime) {
        this.introduceTime = introduceTime;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
}