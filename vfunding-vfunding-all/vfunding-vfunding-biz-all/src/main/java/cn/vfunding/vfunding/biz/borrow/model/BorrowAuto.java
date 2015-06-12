package cn.vfunding.vfunding.biz.borrow.model;

import cn.vfunding.vfunding.common.model.BaseModel;

@SuppressWarnings("serial")
public class BorrowAuto extends  BaseModel{
    private Integer id;

    private Boolean status;

    private Integer tenderType;

    private Integer tenderAccount;

    private Integer tenderScale;

    private Integer videoStatus;

    private Integer sceneStatus;

    private Integer myFriend;

    private Integer notBlack;

    private Integer lateStatus;

    private Integer dianfuStatus;

    private Integer blackStatus;

    private Integer lateTimes;

    private Integer dianfuTimes;

    private Integer blackUser;

    private Integer blackTimes;

    private Integer notLateBlack;

    private Boolean borrowCreditStatus;

    private Integer borrowCreditFirst;

    private Integer borrowCreditLast;

    private Integer borrowStyleStatus;

    private Integer borrowStyle;

    private Integer timelimitStatus;

    private Integer timelimitMonthFirst;

    private Integer timelimitMonthLast;

    private Boolean aprStatus;

    private Byte aprFirst;

    private Byte aprLast;

    private Boolean awardStatus;

    private Float awardFirst;

    private Float awardLast;

    private Boolean vouchStatus;

    private Boolean tuijianStatus;

    private Integer userId;

    private Integer addtime;

    private Boolean fastStatus;

    private Boolean xinStatus;

    private Boolean jinStatus;

    private Byte timelimitDayFirst;

    private Byte timelimitDayLast;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getTenderType() {
        return tenderType;
    }

    public void setTenderType(Integer tenderType) {
        this.tenderType = tenderType;
    }

    public Integer getTenderAccount() {
        return tenderAccount;
    }

    public void setTenderAccount(Integer tenderAccount) {
        this.tenderAccount = tenderAccount;
    }

    public Integer getTenderScale() {
        return tenderScale;
    }

    public void setTenderScale(Integer tenderScale) {
        this.tenderScale = tenderScale;
    }

    public Integer getVideoStatus() {
        return videoStatus;
    }

    public void setVideoStatus(Integer videoStatus) {
        this.videoStatus = videoStatus;
    }

    public Integer getSceneStatus() {
        return sceneStatus;
    }

    public void setSceneStatus(Integer sceneStatus) {
        this.sceneStatus = sceneStatus;
    }

    public Integer getMyFriend() {
        return myFriend;
    }

    public void setMyFriend(Integer myFriend) {
        this.myFriend = myFriend;
    }

    public Integer getNotBlack() {
        return notBlack;
    }

    public void setNotBlack(Integer notBlack) {
        this.notBlack = notBlack;
    }

    public Integer getLateStatus() {
        return lateStatus;
    }

    public void setLateStatus(Integer lateStatus) {
        this.lateStatus = lateStatus;
    }

    public Integer getDianfuStatus() {
        return dianfuStatus;
    }

    public void setDianfuStatus(Integer dianfuStatus) {
        this.dianfuStatus = dianfuStatus;
    }

    public Integer getBlackStatus() {
        return blackStatus;
    }

    public void setBlackStatus(Integer blackStatus) {
        this.blackStatus = blackStatus;
    }

    public Integer getLateTimes() {
        return lateTimes;
    }

    public void setLateTimes(Integer lateTimes) {
        this.lateTimes = lateTimes;
    }

    public Integer getDianfuTimes() {
        return dianfuTimes;
    }

    public void setDianfuTimes(Integer dianfuTimes) {
        this.dianfuTimes = dianfuTimes;
    }

    public Integer getBlackUser() {
        return blackUser;
    }

    public void setBlackUser(Integer blackUser) {
        this.blackUser = blackUser;
    }

    public Integer getBlackTimes() {
        return blackTimes;
    }

    public void setBlackTimes(Integer blackTimes) {
        this.blackTimes = blackTimes;
    }

    public Integer getNotLateBlack() {
        return notLateBlack;
    }

    public void setNotLateBlack(Integer notLateBlack) {
        this.notLateBlack = notLateBlack;
    }

    public Boolean getBorrowCreditStatus() {
        return borrowCreditStatus;
    }

    public void setBorrowCreditStatus(Boolean borrowCreditStatus) {
        this.borrowCreditStatus = borrowCreditStatus;
    }

    public Integer getBorrowCreditFirst() {
        return borrowCreditFirst;
    }

    public void setBorrowCreditFirst(Integer borrowCreditFirst) {
        this.borrowCreditFirst = borrowCreditFirst;
    }

    public Integer getBorrowCreditLast() {
        return borrowCreditLast;
    }

    public void setBorrowCreditLast(Integer borrowCreditLast) {
        this.borrowCreditLast = borrowCreditLast;
    }

    public Integer getBorrowStyleStatus() {
        return borrowStyleStatus;
    }

    public void setBorrowStyleStatus(Integer borrowStyleStatus) {
        this.borrowStyleStatus = borrowStyleStatus;
    }

    public Integer getBorrowStyle() {
		return borrowStyle;
	}

	public void setBorrowStyle(Integer borrowStyle) {
		this.borrowStyle = borrowStyle;
	}

	public Integer getTimelimitStatus() {
        return timelimitStatus;
    }

    public void setTimelimitStatus(Integer timelimitStatus) {
        this.timelimitStatus = timelimitStatus;
    }

    public Integer getTimelimitMonthFirst() {
        return timelimitMonthFirst;
    }

    public void setTimelimitMonthFirst(Integer timelimitMonthFirst) {
        this.timelimitMonthFirst = timelimitMonthFirst;
    }

    public Integer getTimelimitMonthLast() {
        return timelimitMonthLast;
    }

    public void setTimelimitMonthLast(Integer timelimitMonthLast) {
        this.timelimitMonthLast = timelimitMonthLast;
    }

    public Boolean getAprStatus() {
        return aprStatus;
    }

    public void setAprStatus(Boolean aprStatus) {
        this.aprStatus = aprStatus;
    }

    public Byte getAprFirst() {
        return aprFirst;
    }

    public void setAprFirst(Byte aprFirst) {
        this.aprFirst = aprFirst;
    }

    public Byte getAprLast() {
        return aprLast;
    }

    public void setAprLast(Byte aprLast) {
        this.aprLast = aprLast;
    }

    public Boolean getAwardStatus() {
        return awardStatus;
    }

    public void setAwardStatus(Boolean awardStatus) {
        this.awardStatus = awardStatus;
    }

    public Float getAwardFirst() {
        return awardFirst;
    }

    public void setAwardFirst(Float awardFirst) {
        this.awardFirst = awardFirst;
    }

    public Float getAwardLast() {
        return awardLast;
    }

    public void setAwardLast(Float awardLast) {
        this.awardLast = awardLast;
    }

    public Boolean getVouchStatus() {
        return vouchStatus;
    }

    public void setVouchStatus(Boolean vouchStatus) {
        this.vouchStatus = vouchStatus;
    }

    public Boolean getTuijianStatus() {
        return tuijianStatus;
    }

    public void setTuijianStatus(Boolean tuijianStatus) {
        this.tuijianStatus = tuijianStatus;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAddtime() {
        return addtime;
    }

    public void setAddtime(Integer addtime) {
        this.addtime = addtime;
    }

    public Boolean getFastStatus() {
        return fastStatus;
    }

    public void setFastStatus(Boolean fastStatus) {
        this.fastStatus = fastStatus;
    }

    public Boolean getXinStatus() {
        return xinStatus;
    }

    public void setXinStatus(Boolean xinStatus) {
        this.xinStatus = xinStatus;
    }

    public Boolean getJinStatus() {
        return jinStatus;
    }

    public void setJinStatus(Boolean jinStatus) {
        this.jinStatus = jinStatus;
    }

    public Byte getTimelimitDayFirst() {
        return timelimitDayFirst;
    }

    public void setTimelimitDayFirst(Byte timelimitDayFirst) {
        this.timelimitDayFirst = timelimitDayFirst;
    }

    public Byte getTimelimitDayLast() {
        return timelimitDayLast;
    }

    public void setTimelimitDayLast(Byte timelimitDayLast) {
        this.timelimitDayLast = timelimitDayLast;
    }
}