package cn.vfunding.vfunding.biz.report.model;

import java.util.Date;

import cn.vfunding.common.framework.utils.beans.DateUtil;

public class UserActivityZan {
    private Integer id;

    private String userName;

    private Integer activityBonuses;

    private Integer addBonuses;

    private String userPhone;

    private String fileUrls;

    private String status;

    private Date addTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getActivityBonuses() {
        return activityBonuses;
    }

    public void setActivityBonuses(Integer activityBonuses) {
        this.activityBonuses = activityBonuses;
    }

    public Integer getAddBonuses() {
        return addBonuses;
    }

    public void setAddBonuses(Integer addBonuses) {
        this.addBonuses = addBonuses;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone == null ? null : userPhone.trim();
    }

    public String getFileUrls() {
        return fileUrls;
    }

    public void setFileUrls(String fileUrls) {
        this.fileUrls = fileUrls == null ? null : fileUrls.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    
    public String getAddTimeStr(){
    	return DateUtil.parseDateTime(this.addTime);
    }
}